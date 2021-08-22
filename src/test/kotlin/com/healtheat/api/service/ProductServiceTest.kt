package com.healtheat.api.service

import com.healtheat.api.domain.DeleteState
import com.healtheat.api.domain.brand.Brand
import com.healtheat.api.domain.brand.BrandRepository
import com.healtheat.api.domain.product.dto.request.FormProductRequest
import com.healtheat.api.domain.product.dto.response.ProductResponse
import org.junit.jupiter.api.Assertions.assertEquals
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
internal class ProductServiceTest(
    @Autowired private val productService: ProductService,
    @Autowired private val brandRepository: BrandRepository) {

    @Test
    fun save() {
        //given
        val brand = Brand(
            name = "제약"
        )
        brandRepository.save(brand)

        val formProductRequest = FormProductRequest(
            deleteState = DeleteState.N, // 사용여부
            name = "name",
            intakeWay = "intakeWay",
            shelfLifeMonth = 1,
            manufacturingNumber = "manufacturingNumber",
            mainFunctionality = "mainFunctionality",
            storageWay = "storageWay",
            licenseNumber = "licenseNumber",
            packingMaterial = "packingMaterial",
            intakePrecaution = "intakePrecaution",
            standardSpecification = "standardSpecification",
            properties = "properties",
            shape = "properties",
            brandId = 1
        )

        //when
        val productResponse: ProductResponse = productService.save(formProductRequest)

        //then
        assertEquals(productResponse.brand.brandId, formProductRequest.brandId)
        assertEquals(productResponse.brand.name, brand.name)
        assertEquals(productResponse.name, formProductRequest.name)
    }
}
