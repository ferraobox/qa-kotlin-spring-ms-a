package com.ferraobox.qamyapp.application.presenter.usecases.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.UnsupportedJwtException

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.security.SignatureException
import java.util.*

@Component
open class JwtProvider(
    @param:Value("\${app.security.jwtSecret}") private val jwtSecret: String,
    @param:Value("\${app.security.jwtExpirationInMs}") private val jwtExpirationInMs: Int
) {
    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    fun generateToken(authentication: Authentication): String {
        val userPrincipal: UserPrincipal = authentication.principal as UserPrincipal
        return generateToken(userPrincipal.id)
    }

    fun generateToken(customerId: Long?): String {
        val now = Date()
        val expiryDate = Date(now.time + jwtExpirationInMs)
        return Jwts.builder()
            .setSubject(java.lang.Long.toString(customerId!!))
            .setIssuedAt(Date())
            .setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact()
    }

    fun getUserIdFromToken(token: String?): Long {
        val claims: Claims = Jwts.parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(token)
            .getBody()
        return claims.getSubject().toLong()
    }

    fun validateToken(authToken: String?): Boolean {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken)
            return true
        } catch (ex: SignatureException) {
            log.error("Invalid JWT signature")
        } catch (ex: MalformedJwtException) {
            log.error("Invalid JWT token")
        //} catch (ex: ExpiredJwtException) {
          //  log.error("Expired JWT token")
        } catch (ex: UnsupportedJwtException) {
            log.error("Unsupported JWT token")
        } catch (ex: IllegalArgumentException) {
            log.error("JWT claims string is empty.")
        }
        return false
    }
}
