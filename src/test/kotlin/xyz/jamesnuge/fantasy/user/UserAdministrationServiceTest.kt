package xyz.jamesnuge.fantasy.user

import arrow.core.left
import arrow.core.right
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import xyz.jamesnuge.fantasy.EmailService
import xyz.jamesnuge.fantasy.EmailServiceError
import xyz.jamesnuge.fantasy.auth.JwtService
import xyz.jamesnuge.fantasy.repositories.UserRepository

@ExtendWith(MockitoExtension::class)
internal class UserAdministrationServiceTest {

    @Mock
    lateinit var userRepository: UserRepository

    @Mock
    lateinit var emailService: EmailService

    @Mock
    lateinit var jwtUtils: JwtService

    lateinit var userAdministrationService: UserAdministrationService

    @BeforeEach
    fun setup() {
        MockitoAnnotations.openMocks(this::class.java)
        userAdministrationService = UserAdministrationService(userRepository, emailService, jwtUtils, 1000000L)
    }

    @Test
    fun `should return UserAlreadyExistsError when email already registered`() {
        whenever(userRepository.existsByEmail(any())).thenReturn(true)
        val invitation = userAdministrationService.inviteUser("test@test.com")
        assertEquals(UserAdministrationServiceError.UserAlreadyExistsError.left(), invitation)
        verify(userRepository).existsByEmail(eq("test@test.com"))
    }

    @Test
    fun `should call email service with JWT on successful invite`() {
        whenever(userRepository.existsByEmail(any())).thenReturn(false)
        whenever(jwtUtils.generateJwtToken(any() as String, any())).thenReturn("FAKE JWT")
        whenever(emailService.sendEmail(any(), any())).thenReturn(Unit.right())
        val invitation = userAdministrationService.inviteUser("test@test.com")
        assertEquals(Unit.right(), invitation)
        verify(emailService).sendEmail(eq("test@test.com"), eq("Open link with jwt FAKE JWT"))
    }

    @Test
    fun `should return EmailFailureError when email sending fails`() {
        whenever(userRepository.existsByEmail(any())).thenReturn(false)
        whenever(jwtUtils.generateJwtToken(any() as String, any())).thenReturn("FAKE JWT")
        whenever(emailService.sendEmail(any(), any())).thenReturn(EmailServiceError.InvalidRecipientError.left())
        val invitation = userAdministrationService.inviteUser("test@test.com")
        assertEquals(UserAdministrationServiceError.FailedToSendEmailError.left(), invitation)
        verify(emailService).sendEmail(eq("test@test.com"), any())
    }

}