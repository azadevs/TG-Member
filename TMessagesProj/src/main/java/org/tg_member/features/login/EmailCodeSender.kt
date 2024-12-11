package org.tg_member.features.login

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
    val fromEmail = "fromGmail" // Your email
    val toEmail = "toGmail" // Recipient email
    val password = "password"
    val props = Properties()
    var session:Session

    init {
        props["mail.smtp.host"] = "smtp.gmail.com"
        props["mail.smtp.socketFactory.port"] = "465"
        props["mail.smtp.socketFactory.class"] = "javax.net.ssl.SSLSocketFactory"
        props["mail.smtp.auth"] = "true"
        props["mail.smtp.port"] = "465"
        props["mail.transport.protocol"] = "SMTP"

        session = Session.getDefaultInstance(props,
            object : Authenticator() {
                override fun getPasswordAuthentication(): PasswordAuthentication {
                    return PasswordAuthentication(fromEmail, password)
                }
            })
    }

    fun start() {
        Thread {
            try {
                val message = MimeMessage(session)
                message.setFrom(InternetAddress(fromEmail))
                message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(toEmail)
                )
                message.subject = "Davronov Doston"
                message.setText(
                    "My name is Doston" +
                            "\nMy last name Davronov" +
                            "\nI am 20 years old"
                )
                Transport.send(message)
                println("Send message!!!")

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.start()
    }
}