package com.healtheat.api.domain.repository

import com.healtheat.api.config.DataSourceConfig
import com.healtheat.api.domain.product.ProductFunctionality
import com.healtheat.api.domain.product.enum.DeleteState
import com.healtheat.api.domain.product.repository.ProductFunctionalityRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.assertj.core.api.Assertions.assertThat

@ExtendWith(SpringExtension::class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles(value = ["local"])
@Import(DataSourceConfig::class)
class ProductFunctionalityTest(@Autowired val productFunctionalityRepository: ProductFunctionalityRepository) {

    @Test
    fun save() {
        //given
        val productFunction = ProductFunctionality(
            deleteState = DeleteState.N,
            unit = "unit",
            functionalityName = "기능성 이름",
            dayHighLimit = "일일섭취량 상한",
            dayRowLimit = "일일섭취량 하한",
            functionalityText = "추가 정보"
        )
        productFunctionalityRepository.save(productFunction)

        //when
        val savedProductFunction: ProductFunctionality? = productFunctionalityRepository.findByIdOrNull(productFunction.id)

        //then
        assertThat(savedProductFunction?.id).isNotNull
        assertThat(savedProductFunction?.unit).isEqualTo("unit")
    }
}
