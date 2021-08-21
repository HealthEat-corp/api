package com.healtheat.api.domain.repository

import com.healtheat.api.config.DataSourceConfig
import com.healtheat.api.domain.brand.Brand
import com.healtheat.api.domain.brand.BrandRepository
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
class BrandServiceRepositoryTest(@Autowired val brandRepository: BrandRepository) {

    @Test
    fun save() {
        //given
        val brand = Brand(name = "종근당")

        //when
        brandRepository.save(brand)

        //then
        val savedBrands: Brand? = brandRepository.findByIdOrNull(brand.brandId)
        assertThat(savedBrands?.name).isEqualTo(brand.name)
    }
}
