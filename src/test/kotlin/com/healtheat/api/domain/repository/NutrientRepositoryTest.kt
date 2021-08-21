package com.healtheat.api.domain.repository

import com.healtheat.api.config.DataSourceConfig
import com.healtheat.api.domain.nutrient.Nutrient
import com.healtheat.api.domain.DeleteState
import com.healtheat.api.domain.nutrient.NutrientRepository
import org.assertj.core.api.Assertions
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
class NutrientRepositoryTest(@Autowired val nutrientRepository: NutrientRepository) {

    @Test
    fun save() {
        //given
        val nutrient = Nutrient(
            deleteState = DeleteState.N,
            nutrientName = "nutrientName",
            unit = "unit",
            dayHighLimit = "dayHighLimit",
            dayRowLimit = "dayRowLimit",
            functionalityText = "functionalityText"
        )

        //when
        nutrientRepository.save(nutrient)

        //then
        val savedNutrient: Nutrient? = nutrientRepository.findByIdOrNull(nutrient.id)
        Assertions.assertThat(savedNutrient?.id).isNotNull
        Assertions.assertThat(savedNutrient?.nutrientName).isEqualTo("nutrientName")
    }
}
