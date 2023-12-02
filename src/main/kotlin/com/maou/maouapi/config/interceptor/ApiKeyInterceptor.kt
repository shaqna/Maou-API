package com.maou.maouapi.config.interceptor

import com.maou.maouapi.exception.UnauthorizedException
import com.maou.maouapi.repository.key.ApiKeyRepository
import org.springframework.stereotype.Component
import org.springframework.ui.ModelMap
import org.springframework.web.context.request.WebRequest
import org.springframework.web.context.request.WebRequestInterceptor
import java.lang.Exception

@Component
class ApiKeyInterceptor(
    private val apiKeyRepository: ApiKeyRepository
): WebRequestInterceptor {

    companion object {
        private const val HEADER = "X-Api-Key"
    }

    override fun preHandle(request: WebRequest) {
        val apiKey = request.getHeader(HEADER) ?: throw UnauthorizedException()

        if(!apiKeyRepository.existsById(apiKey)) {
            throw UnauthorizedException()
        }
    }

    override fun postHandle(request: WebRequest, model: ModelMap?) {

    }

    override fun afterCompletion(request: WebRequest, ex: Exception?) {

    }
}