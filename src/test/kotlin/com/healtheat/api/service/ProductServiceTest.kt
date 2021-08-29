package com.healtheat.api.service

import com.healtheat.api.RestApiBaseFormat
import com.healtheat.api.domain.DeleteState
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
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
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
    @Autowired private val productRepository: ProductRepository,
    @Autowired private val brandRepository: BrandRepository,
    @Autowired private val functionalRepository: FunctionalRepository) {

    lateinit var brand: Brand
    lateinit var nutrient1: Nutrient
    lateinit var nutrient2: Nutrient
    lateinit var functional1: Functional
    lateinit var functional2: Functional

    @BeforeEach
    fun setUp() {
        this.brand = Brand(name = "제약")
        this.nutrient1 = Nutrient(
            deleteState = DeleteState.N,
            name = "name11",
            unit = "unit",
            dayRowLimit = 1,
            dayHighLimit = 1,
            mainFunctionality = "mainFunctionality"
        )
        this.nutrient2 = Nutrient(
            deleteState = DeleteState.N,
            name = "name22",
            unit = "unit",
            dayRowLimit = 1,
            dayHighLimit = 1,
            mainFunctionality = "mainFunctionality"
        )
        this.functional1 = Functional(
            name = "name1",
            deleteState = DeleteState.N,
            unit = "unit", // 단위
            dayHighLimit = 1, // 일일섭취량 상한
            dayRowLimit = 1, // 일일섭취량 하한
            mainFunctionality = "mainFunctionality", // 주된 기능성
        )
        this.functional2 = Functional(
            name = "name2",
            deleteState = DeleteState.N,
            unit = "unit", // 단위
            dayHighLimit = 1, // 일일섭취량 상한
            dayRowLimit = 1, // 일일섭취량 하한
            mainFunctionality = "mainFunctionality", // 주된 기능성
        )
    }

    @Test
    fun save() {
        //given
        brandRepository.save(brand)
        nutrientRepository.save(nutrient1)
        nutrientRepository.save(nutrient2)
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
        assertEquals(productResponse.nutrient.get(0).name, nutrient1.name)
        assertEquals(productResponse.functional.get(0).name, functional1.name)
    }

    @Test
    fun edit() {
        //given
        brandRepository.save(brand)
        nutrientRepository.save(nutrient1)
        nutrientRepository.save(nutrient2)
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
        val nutrients: MutableList<Nutrient> = nutrientRepository.findByNutrientIdIn(formProductRequest.nutrientId)
        val functionals: MutableList<Functional> = functionalRepository.findByFunctionalIdIn(formProductRequest.functionalId)
        val product = formProductRequest.toEntity(brand = brand, nutrients = nutrients, functionals = functionals)
        productRepository.save(product)

        val editFormProductRequest = FormProductRequest(
            productId = product.productId,
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
            nutrientId = mutableListOf(nutrient1.nutrientId!!),
            functionalId = mutableListOf(functional1.functionalId!!)
        )

        //when
        val productResponse: ProductResponse = productService.edit(editFormProductRequest)

        //then
        assertEquals(1, productResponse.nutrient.size)
        assertEquals(1, productResponse.functional.size)
    }

    @Test
    fun findAll() {
        //given
        brandRepository.save(brand)
        for (i in 1..50) {
            val saveFormProduct = Product(deleteState = DeleteState.N, // 사용여부
                name = "name$i",
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
                brand = brand
            )
            productRepository.save(saveFormProduct)
        }
        val listProductRequest = ListProductRequest(0, 10)

        //when
        val restApiBaseFormat: RestApiBaseFormat = productService.findAll(listProductRequest)

        //then
        assertEquals(0, restApiBaseFormat.pagenation?.nowPage)
        assertEquals(5, restApiBaseFormat.pagenation?.totalPage)
    }
}
