package com.ferraobox.qamyapp.application.presenter.usecases.security

import org.slf4j.LoggerFactory
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import java.io.IOException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
open class JwtAuthenticationEntryPoint : AuthenticationEntryPoint {

    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    @Throws(IOException::class)
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        log.error("Responding with unauthorized error. Message - {}", authException.message)
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
    }
}