package com.unilasalle.helpdesk.security

import com.unilasalle.helpdesk.exception.AuthenticationException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Component
import java.util.Date
import java.util.UUID


@Component
class JwtUtil {

    @Value("\${jwt.expiration}")
    private val expiration: Long? = null

    @Value("\${jwt.secret}")
    private val secret: String? = null

    fun generateToken(id: UUID, authorities: MutableCollection<out GrantedAuthority>): String {

        return Jwts.builder()
            .setSubject(id.toString())
            .claim("roles", authorities)
            .setExpiration(Date(System.currentTimeMillis() + expiration!!))
            .signWith(SignatureAlgorithm.HS512, secret!!.toByteArray())
            .compact()
    }

    fun isValidToken(token: String): Boolean {
        val claims = getClaims(token)
        if (claims.subject == null || claims.expiration == null || Date().after(claims.expiration)) {
            return false
        }
        return true
    }

    fun getSubject(token: String): String {
        return getClaims(token).subject
    }

    private fun getClaims(token: String): Claims {
        try {
            return Jwts.parser()
                .setSigningKey(secret!!.toByteArray())
                .parseClaimsJws(token).body
        } catch (ex: Exception) {
            throw AuthenticationException("Invalid token!", "999")
        }
    }
}