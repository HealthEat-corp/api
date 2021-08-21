package com.healtheat.api.service

import com.healtheat.api.domain.brand.dto.request.CreateBrandRequest
import com.healtheat.api.domain.brand.dto.request.EditBrandRequest
import com.healtheat.api.domain.brand.dto.response.BrandResponse
import com.healtheat.api.domain.brand.Brand
import com.healtheat.api.domain.brand.BrandRepository
import com.healtheat.api.exception.BusinessErrorException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class BrandService(@Autowired private val brandRepository: BrandRepository) {

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

    @Transactional
    fun edit(editBrandRequest: EditBrandRequest): BrandResponse {
        val brand: Brand = brandRepository.findByIdOrNull(editBrandRequest.brandId) ?: throw BusinessErrorException("잘못된 요청입니다.")

        brand.changeName(editBrandRequest.name)

        return BrandResponse(brand)
    }

}
