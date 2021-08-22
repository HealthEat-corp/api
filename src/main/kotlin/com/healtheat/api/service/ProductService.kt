package com.healtheat.api.service

import com.healtheat.api.domain.brand.BrandRepository
import com.healtheat.api.domain.product.dto.request.FormProductRequest
import com.healtheat.api.domain.product.dto.response.ProductResponse
import com.healtheat.api.domain.product.repository.ProductRepository
import com.healtheat.api.exception.BusinessErrorException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ProductService(
    @Autowired private val productRepository: ProductRepository,
    @Autowired private val brandRepository: BrandRepository) {

    fun save(formProductRequest: FormProductRequest): ProductResponse {
        val brand = brandRepository.findByIdOrNull(formProductRequest.brandId) ?: throw BusinessErrorException("잘못된 요청입니다.")

        val product = formProductRequest.toEntity(brand)

        productRepository.save(product)

        return ProductResponse(product)
    }
}
