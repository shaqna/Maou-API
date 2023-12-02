package com.maou.maouapi.repository.product

import com.maou.maouapi.repository.entity.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<ProductEntity, String> {
}