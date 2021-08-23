package com.healtheat.api.service

import com.healtheat.api.domain.DeleteState
import com.healtheat.api.domain.brand.Brand
import com.healtheat.api.domain.brand.BrandRepository
import com.healtheat.api.domain.functional.Functional
import com.healtheat.api.domain.functional.FunctionalRepository
import com.healtheat.api.domain.nutrient.Nutrient
import com.healtheat.api.domain.nutrient.NutrientRepository
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
    @Autowired private val nutrientRepository: NutrientRepository,
    @Autowired private val brandRepository: BrandRepository,
    @Autowired private val functionalRepository: FunctionalRepository) {

    @Test
    fun save() {
        //given
        val brand = Brand(
            name = "제약"
        )
        brandRepository.save(brand)
        val nutrient1 = Nutrient(
            deleteState = DeleteState.N,
            name = "name11",
            unit = "unit",
            dayRowLimit = 1,
            dayHighLimit = 1,
            mainFunctionality = "mainFunctionality"
        )
        val nutrient2 = Nutrient(
            deleteState = DeleteState.N,
            name = "name22",
            unit = "unit",
            dayRowLimit = 1,
            dayHighLimit = 1,
            mainFunctionality = "mainFunctionality"
        )
        nutrientRepository.save(nutrient1)
        nutrientRepository.save(nutrient2)

        val functional1 = Functional(
            name = "name1",
            deleteState = DeleteState.N,
            unit = "unit", // 단위
            dayHighLimit = 1, // 일일섭취량 상한
            dayRowLimit = 1, // 일일섭취량 하한
            mainFunctionality = "mainFunctionality", // 주된 기능성
        )
        val functional2 = Functional(
            name = "name2",
            deleteState = DeleteState.N,
            unit = "unit", // 단위
            dayHighLimit = 1, // 일일섭취량 상한
            dayRowLimit = 1, // 일일섭취량 하한
            mainFunctionality = "mainFunctionality", // 주된 기능성
        )
        functionalRepository.save(functional1)
        functionalRepository.save(functional2)

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
            brandId = 1, // brand key
            nutrientId = mutableListOf(nutrient1.nutrientId!!, nutrient2.nutrientId!!),
            functionalId = mutableListOf(functional1.functionalId!!, functional2.functionalId!!)
        )

        //when
        val productResponse: ProductResponse = productService.save(formProductRequest)

        //then
        assertEquals(productResponse.brand.brandId, formProductRequest.brandId)
        assertEquals(productResponse.brand.name, brand.name)
        assertEquals(productResponse.name, formProductRequest.name)
        assertEquals(productResponse.nutrientName[0], nutrient1.name)
        assertEquals(productResponse.functionalName[0], functional1.name)
    }
}
