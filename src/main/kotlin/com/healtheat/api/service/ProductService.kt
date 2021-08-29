package com.healtheat.api.service

import com.healtheat.api.PagenationResponse
import com.healtheat.api.RestApiBaseFormat
import com.healtheat.api.domain.brand.Brand
import com.healtheat.api.domain.brand.BrandRepository
import com.healtheat.api.domain.functional.Functional
import com.healtheat.api.domain.functional.FunctionalRepository
import com.healtheat.api.domain.nutrient.Nutrient
import com.healtheat.api.domain.nutrient.NutrientRepository
import com.healtheat.api.domain.product.Product
import com.healtheat.api.domain.product.dto.request.FormProductRequest
import com.healtheat.api.domain.product.dto.request.ListProductRequest
import com.healtheat.api.domain.product.dto.response.ProductResponse
import com.healtheat.api.domain.product.repository.ProductRepository
import com.healtheat.api.exception.BusinessErrorException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
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
        // get product, brand, nutrients, functionals
        val product = productRepository.findByIdOrNull(formProductRequest.productId) ?: throw BusinessErrorException("not found productId")
        val brand: Brand = brandRepository.findByIdOrNull(formProductRequest.brandId) ?: throw BusinessErrorException("not found brandId")
        val nutrients: MutableList<Nutrient> = nutrientRepository.findByNutrientIdIn(formProductRequest.nutrientId)
        val functionals: MutableList<Functional> = functionalRepository.findByFunctionalIdIn(formProductRequest.functionalId)

        product.edit(formProductRequest = formProductRequest, brand = brand, nutrients = nutrients, functionals = functionals)
        productRepository.save(product)
        return ProductResponse(product)
    }

    fun findAll(listProductRequest: ListProductRequest): RestApiBaseFormat {
        val pageable = PageRequest.of(listProductRequest.page, listProductRequest.size)

        val pageProducts: Page<Product> = productRepository.findAll(pageable)

        val productResponse: MutableList<ProductResponse> = mutableListOf()
        pageProducts.map { productResponse.add(ProductResponse(it)) }

        return RestApiBaseFormat(data = productResponse, pagenation = PagenationResponse(totalPage = pageProducts.totalPages, nowPage = pageProducts.pageable.pageNumber))
    }
}
