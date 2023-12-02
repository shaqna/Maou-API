package com.maou.maouapi.service

import com.maou.maouapi.exception.NotFoundException
import com.maou.maouapi.model.CreateProductRequest
import com.maou.maouapi.model.ListProductRequest
import com.maou.maouapi.model.ProductResponse
import com.maou.maouapi.model.UpdateProductRequest
import com.maou.maouapi.repository.product.ProductRepository
import com.maou.maouapi.repository.entity.ProductEntity
import com.maou.maouapi.utils.validation.ValidationUtils
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class ProductServiceImpl(
    private val productRepository: ProductRepository,
    private val validator: ValidationUtils
) : ProductService {
    override fun create(createProductRequest: CreateProductRequest): ProductResponse {
        validator.validate(createProductRequest)

        val entity = createProductRequest.run {
            ProductEntity(
                id = id!!,
                name = name!!,
                price = price!!,
                quantity = quantity!!,
                createdAt = Date(),
                updatedAt = null
            )
        }

        productRepository.save(entity)

        return entity.toProductResponse()
    }

    override fun get(id: String): ProductResponse {
        val product = findProductByIdOrThrowNotFound(id)
        return product.toProductResponse()
    }

    override fun update(id: String, updateRequest: UpdateProductRequest): ProductResponse {
        validator.validate(updateRequest)
        val product = findProductByIdOrThrowNotFound(id)

        product.apply {
            name = updateRequest.name
            price = updateRequest.price
            quantity = updateRequest.quantity
            updatedAt = Date()
        }

        productRepository.save(product)

        return product.toProductResponse()
    }

    override fun delete(id: String) {
        val product = findProductByIdOrThrowNotFound(id)
        productRepository.delete(product)
    }

    override fun list(listRequest: ListProductRequest): List<ProductResponse> {
        val page = productRepository.findAll(PageRequest.of(listRequest.pageNumber, listRequest.pageSize))
        val products = page.get().collect(Collectors.toList())

        return products.map{
            it.toProductResponse()
        }
    }

    private fun findProductByIdOrThrowNotFound(id: String): ProductEntity {
        return productRepository.findByIdOrNull(id) ?: throw NotFoundException()
    }

    private fun ProductEntity.toProductResponse(): ProductResponse =
        ProductResponse(
            id, name, price, quantity, createdAt, updatedAt
        )

    private fun List<ProductEntity>.toListProductResponse(): List<ProductResponse> =
        map {
            it.toProductResponse()
        }
}