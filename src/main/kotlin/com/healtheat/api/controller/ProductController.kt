package com.healtheat.api.controller

import com.healtheat.api.RestApiBaseFormat
import com.healtheat.api.domain.product.dto.request.FormProductRequest
import com.healtheat.api.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(@Autowired private val productService: ProductService) {

    @PostMapping("/product")
    fun save(@RequestBody formProductRequest: FormProductRequest): RestApiBaseFormat {
        return RestApiBaseFormat(data = productService.save(formProductRequest))
    }
}
