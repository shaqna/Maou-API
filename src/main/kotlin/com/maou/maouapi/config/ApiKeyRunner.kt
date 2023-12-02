package com.maou.maouapi.config

import com.maou.maouapi.repository.entity.ApiKeyEntity
import com.maou.maouapi.repository.key.ApiKeyRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class ApiKeyRunner (
    private val keyRepository: ApiKeyRepository
): ApplicationRunner {

    private val apiKey = "maou.api.key"

    override fun run(args: ApplicationArguments?) {
        if(!keyRepository.existsById(apiKey)) {
            val apiKeyEntity = ApiKeyEntity(id = apiKey)
            keyRepository.save(apiKeyEntity)
        }
    }
}