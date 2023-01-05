package xyz.jamesnuge.skipdata.auth

import javax.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import xyz.jamesnuge.skipdata.user.UserAuthService
import xyz.jamesnuge.skipdata.user.UserSignupError


@RestController
@RequestMapping("/api/auth", consumes = ["application/json"])
class AuthController(
    private val userAuthService: UserAuthService,
) {

    @RequestMapping("/login")
    fun login(@Valid @RequestBody request: LoginRequest): JwtResponse = with(request) {
        return userAuthService.signInUser(email, password)
    }

    @RequestMapping("/signup")
    fun signup(@Valid @RequestBody request: UserProfileRequest): ResponseEntity<Any> = userAuthService.signup(request).fold(
        { mapUserSignupErrorToResponse(it) },
        { ResponseEntity.ok().build() }
    )

}

fun mapUserSignupErrorToResponse(error: UserSignupError): ResponseEntity<Any> = when (error) {
    UserSignupError.UserAlreadyExistsError -> ResponseEntity.badRequest().body("User with that email already exists")
}

data class LoginRequest(
    val email: String,
    val password: String
)

data class UserProfileRequest(
    val email: String,
    val firstName: String,
    val lastName: String,
    val displayName: String,
    val password: String
)

data class JwtResponse(val jwt: String, val email: String, val roles: List<String>)