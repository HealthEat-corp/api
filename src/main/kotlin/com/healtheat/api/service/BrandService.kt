package com.healtheat.api.service

import com.healtheat.api.controller.dto.request.CreateBrandRequest
import com.healtheat.api.controller.dto.response.BrandResponse
import com.healtheat.api.domain.product.Brand
import com.healtheat.api.domain.product.repository.BrandRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class BrandService(@Autowired val brandRepository: BrandRepository) {

    @Transactional
    fun save(createBrandRequest: CreateBrandRequest): BrandResponse {
        val brand = Brand(name = createBrandRequest.name)

        brandRepository.save(brand)

        return BrandResponse(brand)
    }

    fun findAll(): ArrayList<BrandResponse> {
        val brands: MutableList<Brand> = brandRepository.findAll()

        val brandsResponse: ArrayList<BrandResponse> = ArrayList()

        brands.forEach{brandsResponse.add(BrandResponse(it))}

        return brandsResponse
    }
}
