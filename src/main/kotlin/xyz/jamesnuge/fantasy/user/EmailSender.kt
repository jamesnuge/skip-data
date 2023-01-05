package xyz.jamesnuge.fantasy.user

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Profile
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import xyz.jamesnuge.fantasy.email.EmailService
import xyz.jamesnuge.fantasy.repositories.UserRepository

// @Component
//@Profile(value = ["dev", "ci"])
class EmailSender(
    private val emailService: EmailService
): ApplicationRunner {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(EmailSender::class.java)
    }

    override fun run(args: ApplicationArguments?) {
        val sentMessage = emailService.sendEmail("james.nugent89@gmail.com", "Test email attempt", "")
        if (sentMessage.isRight()) {
            logger.warn("++++ SENT MAIL SUCCESSFULLY ++++")
        } else {
            logger.warn("++++ UNABLE TO SEND MAIL ++++")
        }
    }

}