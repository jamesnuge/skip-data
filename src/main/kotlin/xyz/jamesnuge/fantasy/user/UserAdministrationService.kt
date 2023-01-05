package xyz.jamesnuge.fantasy.user

import arrow.core.Either
import arrow.core.left
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import xyz.jamesnuge.fantasy.auth.JwtService
import xyz.jamesnuge.fantasy.email.EmailService
import xyz.jamesnuge.fantasy.repositories.UserRepository
import xyz.jamesnuge.fantasy.user.UserAdministrationServiceError.UserAlreadyExistsError

@Service
class UserAdministrationService(
    private val userRepository: UserRepository,
    private val emailService: EmailService,
    private val jwtUtils: JwtService,
    @Value("\${fantasy.auth.invite.expiry}")
    private val inviteExpiry: Long
) {
    fun inviteUser(email: String): Either<UserAdministrationServiceError, Unit> {
        if (userRepository.existsByEmail(email)) return UserAlreadyExistsError.left()
        val jwt = jwtUtils.generateJwtToken(email, inviteExpiry)
        return emailService.sendEmail(email, "", """"Open link with jwt $jwt""")
            .bimap({ UserAdministrationServiceError.FailedToSendEmailError }, { });
    }
}

sealed class UserAdministrationServiceError {
    object UserAlreadyExistsError: UserAdministrationServiceError()
    object FailedToSendEmailError: UserAdministrationServiceError()
}