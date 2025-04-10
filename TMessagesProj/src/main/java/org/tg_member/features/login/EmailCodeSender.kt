package org.tg_member.features.login

import java.util.Properties
import kotlin.random.Random
import javax.mail.Authenticator
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.AddressException
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

object EmailCodeSender {

    var code:Int = 0

    fun sendEmail(email:String) {
        try {

            val stringSenderEmail = "tgmemberpro@gmail.com"
            val stringReceiverEmail = email
            val stringPasswordSenderEmail = "liba ptjc knss fnjo"

            val stringHost = "smtp.gmail.com"

            val properties: Properties = System.getProperties()

            properties.setProperty("mail.transport.protocol", "smtp")
            properties.setProperty("mail.host", stringHost)
            properties["mail.smtp.host"] = stringHost
            properties["mail.smtp.port"] = "465"
            properties["mail.smtp.socketFactory.fallback"] = "false"
            properties.setProperty("mail.smtp.quitwait", "false")
            properties["mail.smtp.socketFactory.port"] = "465"
            properties["mail.smtp.starttls.enable"] = "true"
            properties["mail.smtp.socketFactory.class"] = "javax.net.ssl.SSLSocketFactory"
            properties["mail.smtp.ssl.enable"] = "true"
            properties["mail.smtp.auth"] = "true"

            val session: Session = Session.getInstance(properties, object : Authenticator() {
                override fun getPasswordAuthentication(): PasswordAuthentication {
                    return PasswordAuthentication(
                        stringSenderEmail,
                        stringPasswordSenderEmail
                    )
                }
            })

            code = Random.nextInt(10000, 99999)

            val mimeMessage = MimeMessage(session)
            mimeMessage.addRecipients(Message.RecipientType.TO, InternetAddress.parse(stringReceiverEmail))
            mimeMessage.subject = "Tg Member verification code"
            mimeMessage.setText("Bu kod dasturga kirishingiz uchun kerak: $code")

            val thread = Thread {
                try {
                    Transport.send(mimeMessage)
                } catch (e: MessagingException) {
                    e.printStackTrace()
                }
            }
            thread.start()
        } catch (e: AddressException) {
            e.printStackTrace()
        } catch (e: MessagingException) {
            e.printStackTrace()
        }
    }

    fun checkCode(password:Int):Boolean {
        return code == password
    }
}