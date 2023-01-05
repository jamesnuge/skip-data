package xyz.jamesnuge.skipdata.user

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import xyz.jamesnuge.skipdata.auth.JwtResponse
import xyz.jamesnuge.skipdata.auth.JwtService
import xyz.jamesnuge.skipdata.auth.UserProfileRequest
import xyz.jamesnuge.skipdata.repositories.UserRepository
import java.util.stream.Collectors

@Service
class UserAuthService(
    private val authenticationManager: AuthenticationManager,
    private val jwtUtils: JwtService,
    private val passwordEncoder: PasswordEncoder,
    private val userRepository: UserRepository
) {

    fun signInUser(email: String, password: String): JwtResponse {
        val authentication: Authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(email, password)
        )
        SecurityContextHolder.getContext().authentication = authentication
        val jwt: String = jwtUtils.generateJwtToken(authentication)

        val userDetails: UserDetails = authentication.principal as UserDetails
        val roles: List<String> = userDetails.authorities.stream()
            .map { it.authority }
            .collect(Collectors.toList())
        return JwtResponse(
            jwt,
            userDetails.username,
            roles
        )
    }

    fun signup(userProfileRequest: UserProfileRequest): Either<UserSignupError, Unit> = with(userProfileRequest) {
        if (userRepository.existsByEmail(userProfileRequest.email)) {
            UserSignupError.UserAlreadyExistsError.left()
        } else {
            val newUserProfile = UserProfile(
                email = userProfileRequest.email,
                password = passwordEncoder.encode(userProfileRequest.password),
                firstName = userProfileRequest.firstName,
                lastName = userProfileRequest.lastName,
                role = Role.ADMIN,
                displayName = userProfileRequest.displayName
            )
            userRepository.save(newUserProfile)
            Unit.right()
        }
    }
}

sealed class UserSignupError {
    object UserAlreadyExistsError: UserSignupError()
}