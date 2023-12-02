package com.maou.maouapi.controller

import com.maou.maouapi.model.*
import com.maou.maouapi.service.ProductService
import org.springframework.web.bind.annotation.*

@RestController
class ProductController(
    private val service: ProductService
) {

    companion object {
        private const val ENDPOINT = "/api/products"
        private const val JSON_FORMAT = "application/json"
    }


    @PostMapping(
        value = [ENDPOINT],
        produces = [JSON_FORMAT],
        consumes = [JSON_FORMAT]
    )
    fun createProduct(@RequestBody body: CreateProductRequest): ApiResponse<ProductResponse> {
        val productResponse = service.create(body)
        return ApiResponse(
            code = 200,
            status = "Success",
            data = productResponse
        )
    }

    @GetMapping(
        value = ["$ENDPOINT/{idProduct}"],
        produces = [JSON_FORMAT]
    )
    fun getProduct(@PathVariable("idProduct") id: String): ApiResponse<ProductResponse> {
        val product = service.get(id)
        return ApiResponse(
            code = 200,
            status = "Success",
            data = product
        )
    }

    @PutMapping(
        value = ["$ENDPOINT/{idProduct}"],
        produces = [JSON_FORMAT],
        consumes = [JSON_FORMAT]
    )
    fun updateProduct(
        @PathVariable("idProduct") id: String,
        @RequestBody body: UpdateProductRequest
    ): ApiResponse<ProductResponse> {
        val response = service.update(id, body)

        return ApiResponse(
            code = 200,
            status = "Success",
            data = response
        )
    }

    @DeleteMapping(
        value = ["$ENDPOINT/{idProduct}"],
        produces = [JSON_FORMAT]
    )
    fun deleteProduct(@PathVariable("idProduct") id: String): ApiResponse<String> {
        service.delete(id)
        return ApiResponse(
            code = 200,
            status = "Success",
            data = id
        )
    }

    @GetMapping(
        value = [ENDPOINT],
        produces = [JSON_FORMAT]
    )
    fun listProducts(
        @RequestParam(value = "pageNumber", defaultValue = "0") pageNumber:Int,
        @RequestParam(value = "pageSize", defaultValue = "10") pageSize: Int
    ): ApiResponse<List<ProductResponse>> {
        val request = ListProductRequest(pageNumber, pageSize)
        val products = service.list(request)

        return ApiResponse(
            code = 200,
            status = "Success",
            data = products
        )
    }
}