package com.maou.maouapi.repository.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "api_keys")
data class ApiKeyEntity(
    @Id
    val id: String
)
