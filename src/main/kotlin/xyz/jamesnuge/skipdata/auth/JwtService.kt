package xyz.jamesnuge.skipdata.auth

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.SignatureException
import io.jsonwebtoken.UnsupportedJwtException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.time.Instant
import java.util.Date

@Component
class JwtService(
    @Value("\${fantasy.jwt.secret}")
    private val jwtSecret: String,
    @Value("\${fantasy.jwt.timeout}")
    private val jwtExpirationInMs: Int,
) {
    companion object {
        val logger: Logger = LoggerFactory.getLogger(this::class.java)
    }

    fun generateJwtToken(authentication: Authentication, expiryInMs: Long = jwtExpirationInMs.toLong()): String =
        generateJwtToken((authentication.principal as UserDetails).username)

    fun generateJwtToken(subject: String, expiryInMs: Long = jwtExpirationInMs.toLong()): String = Jwts.builder().setSubject(subject)
            .setIssuedAt(Date.from(Instant.now()))
            .setExpiration(Date.from(Instant.now().plusMillis(expiryInMs)))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact()

    fun getUserNameFromJwtToken(token: String): String = Jwts.parser()
        .setSigningKey(jwtSecret)
        .parseClaimsJws(token)
        .body
        .subject

    fun validateJwtToken(authToken: String): Boolean {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (e: SignatureException) {
            logger.error("Invalid JWT signature: {}", e.message)
        } catch (e: MalformedJwtException) {
            logger.error("Invalid JWT token: {}", e.message)
        } catch (e: ExpiredJwtException) {
            logger.error("JWT token is expired: {}", e.message)
        } catch (e: UnsupportedJwtException) {
            logger.error("JWT token is unsupported: {}", e.message)
        } catch (e: IllegalArgumentException) {
            logger.error("JWT claims string is empty: {}", e.message)
        }
        return false;
    }

}