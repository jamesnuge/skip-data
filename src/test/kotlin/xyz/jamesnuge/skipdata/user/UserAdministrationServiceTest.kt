package xyz.jamesnuge.skipdata.user

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension
import xyz.jamesnuge.skipdata.auth.JwtService
import xyz.jamesnuge.skipdata.repositories.UserRepository

@ExtendWith(MockitoExtension::class)
internal class UserAdministrationServiceTest {

    @Mock
    lateinit var userRepository: UserRepository

    @Mock
    lateinit var jwtUtils: JwtService

    lateinit var userAdministrationService: UserAdministrationService

    @BeforeEach
    fun setup() {
        MockitoAnnotations.openMocks(this::class.java)
        userAdministrationService = UserAdministrationService(userRepository, jwtUtils, 1000000L)
    }

}