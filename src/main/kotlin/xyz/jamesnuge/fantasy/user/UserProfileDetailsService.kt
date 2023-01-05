package xyz.jamesnuge.fantasy.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import xyz.jamesnuge.fantasy.repositories.UserRepository

// Implementation of the Spring security UserDetailsService
@Service
class UserProfileDetailsService(@Autowired val userRepository: UserRepository): UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        return userRepository.findByEmail(username?:"")
            .orElseThrow { UsernameNotFoundException("User with email $username not found") }
            .toUserDetails()
    }
}