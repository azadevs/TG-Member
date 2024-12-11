package org.tg_member.features.login

import java.util.Properties
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
    fun sendEmail() {
        try {

            val stringSenderEmail = "from account"
            val stringReceiverEmail = "to account"
            val stringPasswordSenderEmail = "16X code"

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

            val mimeMessage = MimeMessage(session)
            mimeMessage.addRecipients(Message.RecipientType.TO, InternetAddress.parse(stringReceiverEmail))
            mimeMessage.subject = "Subject: Android App email"
            mimeMessage.setText("Hello Programmer, \n\nProgrammer World has sent you this 2nd email. \n\n Cheers!\nProgrammer World")

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
}