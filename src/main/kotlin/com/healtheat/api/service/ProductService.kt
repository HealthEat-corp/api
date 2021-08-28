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
        // get brand
        val brand: Brand = brandRepository.findByIdOrNull(formProductRequest.brandId) ?: throw BusinessErrorException("not found brandId")

        // get nutrient
        val nutrients: MutableList<Nutrient> = nutrientRepository.findByNutrientIdIn(formProductRequest.nutrientId)

        // get functional
        val functionals: MutableList<Functional> = functionalRepository.findByFunctionalIdIn(formProductRequest.functionalId)

        // set product
        val product = formProductRequest.toEntity(brand = brand, nutrients = nutrients, functionals = functionals)
        productRepository.save(product)

        return ProductResponse(product)
    }

    @Transactional
    fun edit(formProductRequest: FormProductRequest): ProductResponse {
        val product = productRepository.findByIdOrNull(formProductRequest.productId) ?: throw BusinessErrorException("not found productId")
        val brand: Brand = brandRepository.findByIdOrNull(formProductRequest.brandId) ?: throw BusinessErrorException("not found brandId")
        val nutrients: MutableList<Nutrient> = nutrientRepository.findByNutrientIdIn(formProductRequest.nutrientId)
        val functionals: MutableList<Functional> = functionalRepository.findByFunctionalIdIn(formProductRequest.functionalId)

        product.edit(formProductRequest = formProductRequest, brand = brand, nutrients = nutrients, functionals = functionals)
        productRepository.save(product)
        return ProductResponse(product)
    }
}
