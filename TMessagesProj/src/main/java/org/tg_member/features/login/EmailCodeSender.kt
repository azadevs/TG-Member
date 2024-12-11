package org.tg_member.features.login

import android.util.Log
import java.util.Properties
import javax.mail.Authenticator
import javax.mail.Message
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

object EmailCodeSender {

    val host = "smtp.gmail.com"
    val port = "465"
    val fromEmail = "promessengertg@gmail.com" // Your email
    val toEmail = "helloworld282003@gmail.com" // Recipient email
    val password = "Promessenger200" // Your email password or App password if 2FA is enabled

    val properties = Properties().apply {
        put("mail.smtp.auth", "true")
        put("mail.smtp.starttls.enable", "true")
        put("mail.smtp.host", host)
        put("mail.smtp.port", port)
        setProperty("mail.transport.protocol", "smtp")
        setProperty("mail.host", host)
        put("mail.smtp.socketFactory.port", "465")
        put("mail.smtp.socketFactory.class",
            "javax.net.ssl.SSLSocketFactory")
        put("mail.smtp.socketFactory.fallback", "false")

    }

    val session = Session.getInstance(properties, object : Authenticator() {
        override fun getPasswordAuthentication(): PasswordAuthentication {
            return PasswordAuthentication(fromEmail, password) 
        }
    })

    fun start(){
        try {
            val message = MimeMessage(session).apply {
                setFrom(InternetAddress(fromEmail))
                setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail))
                subject = "Test Email from Kotlin"
                setText("This is a test email sent from Kotlin.")
            }

            Transport.send(message)
            println("Email sent successfully.")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}