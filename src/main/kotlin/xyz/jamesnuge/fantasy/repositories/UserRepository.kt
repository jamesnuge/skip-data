package xyz.jamesnuge.fantasy.repositories

import org.springframework.data.repository.CrudRepository
import xyz.jamesnuge.fantasy.user.UserProfile
import java.util.Optional

interface UserRepository: CrudRepository<UserProfile, Long> {
    fun findByEmail(email: String): Optional<UserProfile>
    fun existsByEmail(email: String): Boolean
}

