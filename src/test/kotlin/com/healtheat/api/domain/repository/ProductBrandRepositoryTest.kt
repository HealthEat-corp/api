package com.healtheat.api.domain.repository

import com.healtheat.api.config.DataSourceConfig
import com.healtheat.api.domain.product.ProductBrand
import com.healtheat.api.domain.product.repository.ProductBrandRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension

import org.assertj.core.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull

@ExtendWith(SpringExtension::class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles(value = ["local"])
@Import(DataSourceConfig::class)
class ProductBrandRepositoryTest(@Autowired val productBrandRepository: ProductBrandRepository) {

    @Test
    fun save() {
        //given
        val productBrand = ProductBrand(brandName = "종근당")

        //when
        productBrandRepository.save(productBrand)

        //then
        val savedProductBrands: ProductBrand? = productBrandRepository.findByIdOrNull(productBrand.id)
        assertThat(savedProductBrands?.brandName).isEqualTo(productBrand.brandName)
    }
}
