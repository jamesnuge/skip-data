package xyz.jamesnuge.fantasy.user

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Profile
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import xyz.jamesnuge.fantasy.repositories.UserRepository

@Component
@Profile(value = ["dev", "ci"])
class UserSeeder(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
): ApplicationRunner {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(UserSeeder::class.java)
    }

    override fun run(args: ApplicationArguments?) {
        if (!userRepository.existsByEmail("test@test.com")) {
            logger.info("Bootstrapping test user")
            userRepository.save(UserProfile(
                email = "test@test.com",
                displayName = "test",
                role = Role.ADMIN,
                firstName = "first",
                lastName = "last",
                password = passwordEncoder.encode("password")
            ))
        }
    }

}