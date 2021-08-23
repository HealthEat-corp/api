package com.healtheat.api.domain.nutrient

import com.healtheat.api.domain.DeleteState
import org.assertj.core.api.Assertions
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
internal class NutrientRepositoryTest(@Autowired private val nutrientRepository: NutrientRepository) {

    @Test
    fun findByNutrientIdIn() {
        //given
        val nutrient1 = Nutrient(
            deleteState = DeleteState.N,
            name = "name",
            unit = "unit",
            dayRowLimit = 1,
            dayHighLimit = 1,
            mainFunctionality = "mainFunctionality"
        )
        val nutrient2 = Nutrient(
            deleteState = DeleteState.N,
            name = "name",
            unit = "unit",
            dayRowLimit = 1,
            dayHighLimit = 1,
            mainFunctionality = "mainFunctionality"
        )
        nutrientRepository.save(nutrient1)
        nutrientRepository.save(nutrient2)

//        val nutrientIds: MutableList<Long> = mutableListOf(nutrient1.nutrientId!!, nutrient2.nutrientId!!)
        val nutrientIds: MutableList<Long> = mutableListOf()

        //when
        val nutrients: MutableList<Nutrient> = nutrientRepository.findByNutrientIdIn(nutrientIds)

        //then
        Assertions.assertThat(nutrients.size).isEqualTo(nutrientIds.size)
    }
}
