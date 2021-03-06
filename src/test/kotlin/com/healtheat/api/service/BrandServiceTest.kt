package com.healtheat.api.service

import com.healtheat.api.domain.brand.dto.request.CreateBrandRequest
import com.healtheat.api.domain.brand.dto.request.EditBrandRequest
import com.healtheat.api.domain.brand.dto.response.BrandResponse
import com.healtheat.api.domain.brand.Brand
import com.healtheat.api.domain.brand.BrandRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional

@ActiveProfiles(value = ["local"])
@ExtendWith(SpringExtension::class)
@SpringBootTest
@Transactional
class BrandServiceTest(
    @Autowired val brandService: BrandService,
    @Autowired val brandRepository: BrandRepository
) {

    @Test
    fun save() {
        //given
        val createBrandRequest = CreateBrandRequest(name = "제약")

        //when
        val brandResponse:BrandResponse = brandService.save(createBrandRequest)

        //then
        assertThat(brandResponse.name).isEqualTo(createBrandRequest.name)
    }

    @Test
    fun edit() {
        //given
        val brand = Brand(name = "제약")
        brandRepository.save(brand)
        val editBrandRequest = EditBrandRequest(brandId = brand.brandId!!, "제약제약")

        //when
        val brandResponse:BrandResponse = brandService.edit(editBrandRequest)

        //then
        assertThat(brandResponse.name).isEqualTo(editBrandRequest.name)
    }
}
