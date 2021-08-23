package com.healtheat.api.service

import com.healtheat.api.domain.brand.Brand
import com.healtheat.api.domain.brand.BrandRepository
import com.healtheat.api.domain.nutrient.Nutrient
import com.healtheat.api.domain.nutrient.NutrientRepository
import com.healtheat.api.domain.product.ProductNutrient
import com.healtheat.api.domain.product.dto.request.FormProductRequest
import com.healtheat.api.domain.product.dto.response.ProductResponse
import com.healtheat.api.domain.product.repository.ProductRepository
import com.healtheat.api.exception.BusinessErrorException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ProductService(
    @Autowired private val productRepository: ProductRepository,
    @Autowired private val brandRepository: BrandRepository,
    @Autowired private val nutrientRepository: NutrientRepository) {

    @Transactional
    fun save(formProductRequest: FormProductRequest): ProductResponse {
        val brand: Brand = brandRepository.findByIdOrNull(formProductRequest.brandId) ?: throw BusinessErrorException("잘못된 요청입니다.")

        val product = formProductRequest.toEntity(brand)


        val nutrients: MutableList<Nutrient> = nutrientRepository.findByNutrientIdIn(formProductRequest.nutrientId)
        nutrients.forEach { nutrient ->
            product.addProductNutrient(ProductNutrient(product = product, nutrient = nutrient))
        }

        productRepository.save(product)
        return ProductResponse(product)
    }
}
