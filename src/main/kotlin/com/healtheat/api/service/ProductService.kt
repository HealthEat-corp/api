package com.healtheat.api.service

import com.healtheat.api.domain.brand.Brand
import com.healtheat.api.domain.brand.BrandRepository
import com.healtheat.api.domain.functional.Functional
import com.healtheat.api.domain.functional.FunctionalRepository
import com.healtheat.api.domain.nutrient.Nutrient
import com.healtheat.api.domain.nutrient.NutrientRepository
import com.healtheat.api.domain.product.ProductFunctional
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
    @Autowired private val nutrientRepository: NutrientRepository,
    @Autowired private val functionalRepository: FunctionalRepository) {

    @Transactional
    fun save(formProductRequest: FormProductRequest): ProductResponse {
        val brand: Brand = brandRepository.findByIdOrNull(formProductRequest.brandId) ?: throw BusinessErrorException("잘못된 요청입니다.")

        val product = formProductRequest.toEntity(brand)

        // nutrient 셋팅
        val nutrients: MutableList<Nutrient> = nutrientRepository.findByNutrientIdIn(formProductRequest.nutrientId)
        nutrients.forEach { nutrient ->
            product.addProductNutrient(ProductNutrient(product = product, nutrient = nutrient))
        }
        // functional 셋팅
        val functionals: MutableList<Functional> = functionalRepository.findByFunctionalIdIn(formProductRequest.functionalId)
        functionals.forEach { functional ->
            product.addProductFunctional(ProductFunctional(product = product, functional = functional))
        }

        productRepository.save(product)
        return ProductResponse(product)
    }
}
