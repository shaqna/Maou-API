package com.maou.maouapi.repository.key

import com.maou.maouapi.repository.entity.ApiKeyEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ApiKeyRepository: JpaRepository<ApiKeyEntity, String> {
}