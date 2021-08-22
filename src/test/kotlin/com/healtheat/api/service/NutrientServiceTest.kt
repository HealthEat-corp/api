package com.healtheat.api.service

import com.healtheat.api.domain.DeleteState
import com.healtheat.api.domain.nutrient.Nutrient
import com.healtheat.api.domain.nutrient.NutrientRepository
import com.healtheat.api.domain.nutrient.dto.request.FormNutrientRequest
import com.healtheat.api.domain.nutrient.dto.response.NutrientResponse
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
internal class NutrientServiceTest(
    @Autowired private val nutrientService: NutrientService,
    @Autowired private val nutrientRepository: NutrientRepository) {

    @Test
    fun save() {
        //given
        val formNutrientRequest = FormNutrientRequest(
            name = "name",
            deleteStatus = DeleteState.N,
            unit = "unit", // 단위
            dayHighLimit = 1, // 일일섭취량 상한
            dayRowLimit = 1, // 일일섭취량 하한
            mainFunctionality = "mainFunctionality", // 주된 기능성
        )

        //when
        val nutrientResponse: NutrientResponse = nutrientService.save(formNutrientRequest)

        //then
        assertEquals(formNutrientRequest.name, nutrientResponse.name)
    }

    @Test
    fun edit() {
        //given
        val nutrient = Nutrient(
            name = "name",
            deleteState = DeleteState.N,
            unit = "unit", // 단위
            dayHighLimit = 1, // 일일섭취량 상한
            dayRowLimit = 1, // 일일섭취량 하한
            mainFunctionality = "mainFunctionality", // 주된 기능성
        )
        nutrientRepository.save(nutrient)
        val formNutrientRequest: FormNutrientRequest = FormNutrientRequest(
            nutrientId = nutrient.nutrientId,
            name = "changeName",
            deleteStatus = DeleteState.N,
            unit = "unit", // 단위
            dayHighLimit = 1, // 일일섭취량 상한
            dayRowLimit = 1, // 일일섭취량 하한
            mainFunctionality = "mainFunctionality", // 주된 기능성
        )
        //when
        val nutrientResponse: NutrientResponse = nutrientService.edit(formNutrientRequest)

        //then
        assertEquals(formNutrientRequest.name, nutrientResponse.name)
    }
}
