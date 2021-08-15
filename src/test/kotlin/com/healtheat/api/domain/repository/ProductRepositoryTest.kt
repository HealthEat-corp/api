package com.healtheat.api.domain.repository

import com.healtheat.api.config.DataSourceConfig
import com.healtheat.api.domain.product.Product
import com.healtheat.api.domain.product.ProductBrand
import com.healtheat.api.domain.product.ProductFunctionality
import com.healtheat.api.domain.product.enum.DeleteState
import com.healtheat.api.domain.product.repository.ProductBrandRepository
import com.healtheat.api.domain.product.repository.ProductFunctionalityRepository
import com.healtheat.api.domain.product.repository.ProductRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles(value = ["local"])
@Import(DataSourceConfig::class)
class ProductRepositoryTest(
    @Autowired val productRepository: ProductRepository,
    @Autowired val productBrandRepository: ProductBrandRepository,
    @Autowired val productFunctionalityRepository: ProductFunctionalityRepository,) {

    @Test
    fun save() {
        //given
        val productBrand = ProductBrand(brandName = "종근당")
        productBrandRepository.save(productBrand)

        val productFunction = ProductFunctionality(
            deleteState = DeleteState.N,
            unit = "unit",
            functionalityName = "기능성 이름",
            dayHighLimit = "일일섭취량 상한",
            dayRowLimit = "일일섭취량 하한",
            functionalityText = "추가 정보"
        )
        productFunctionalityRepository.save(productFunction)

        val product = Product(
            productName = "productName",
            deleteState = DeleteState.N,
            intakeWay = "섭취방법",
            shelfLifeMonth = 5,
            manufacturingNumber = "제조번호",
            functionalityText = "주된 기능성",
            storageWay = "보관 방법",
            licenseNumber = "허가번호",
            packingMaterial = "포장재질",
            intakePrecaution = "섭취주의사항",
            standardSpecification = "기준규격",
            properties = "성상",
            shape = "형태",
            productBrand = productBrand,
            productFunctionality = productFunction
        )

        //when
        productRepository.save(product)

        //then
        val savedProduct: Product? = productRepository.findByIdOrNull(product.id)

        assertThat(savedProduct?.productName).isEqualTo(product.productName);
        assertThat(savedProduct?.deleteState).isEqualTo(product.deleteState);
        assertThat(savedProduct?.productBrand?.id).isNotNull
        assertThat(savedProduct?.productFunctionality?.id).isNotNull
    }

}
