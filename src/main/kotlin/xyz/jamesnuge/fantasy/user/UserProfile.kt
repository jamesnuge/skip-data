package xyz.jamesnuge.fantasy.user

import javax.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
data class UserProfile(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Enumerated(EnumType.STRING)
    val role: Role,
    val displayName: String,
    val password: String,
    val email: String,
    val firstName: String,
    val lastName: String,
)

enum class Role {
    ADMIN,
    MEMBER
}

fun UserProfile.toUserDetails(): UserDetails = UserProfileDetails(
    authorities = mutableListOf(),
    password = this.password,
    username = this.email
)

class UserProfileDetails(
    private val authorities: MutableCollection<out GrantedAuthority>,
    private val password: String,
    private val username: String,
): UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = authorities
    override fun getPassword(): String = password
    override fun getUsername(): String = username

    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isEnabled(): Boolean = true

}