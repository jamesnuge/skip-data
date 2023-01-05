package xyz.jamesnuge.fantasy.email

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import org.springframework.mail.MailException
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService(
    val javaMailSender: JavaMailSender
) {

    fun sendEmail(email: String, subject: String, template: Any): Either<EmailServiceError, Unit> {
        val message = SimpleMailMessage()
        message.setTo(email)
        message.setSubject(subject)
        message.setText("This is a test email")
        return EmailUtil.sendMessage(message, javaMailSender)
    }

}

sealed class EmailServiceError {
    object InvalidRecipientError: EmailServiceError()
}

object EmailUtil {
    fun sendMessage(message: SimpleMailMessage, javaMailSender: JavaMailSender): Either<EmailServiceError, Unit> {
        return try {
            javaMailSender.send(message)
            Unit.right()
        } catch (e: MailException) {
            EmailServiceError.InvalidRecipientError.left()
        }
    }
}
