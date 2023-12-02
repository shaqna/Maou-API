package com.maou.maouapi.service

import com.maou.maouapi.model.CreateProductRequest
import com.maou.maouapi.model.ListProductRequest
import com.maou.maouapi.model.ProductResponse
import com.maou.maouapi.model.UpdateProductRequest

interface ProductService {

    fun create(createProductRequest: CreateProductRequest): ProductResponse
    fun get(id: String): ProductResponse
    fun update(id: String, updateRequest: UpdateProductRequest): ProductResponse
    fun delete(id: String)
    fun list(listRequest: ListProductRequest): List<ProductResponse>
}