package xyz.jamesnuge.skipdata.user

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import xyz.jamesnuge.skipdata.auth.JwtService
import xyz.jamesnuge.skipdata.repositories.UserRepository

@Service
class UserAdministrationService(
    private val userRepository: UserRepository,
    private val jwtUtils: JwtService,
    @Value("\${fantasy.auth.invite.expiry}")
    private val inviteExpiry: Long
) {

}

sealed class UserAdministrationServiceError {
    object UserAlreadyExistsError: UserAdministrationServiceError()
    object FailedToSendEmailError: UserAdministrationServiceError()
}