package com.healtheat.api.service

import com.healtheat.api.domain.DeleteState
import com.healtheat.api.domain.functional.Functional
import com.healtheat.api.domain.functional.FunctionalRepository
import com.healtheat.api.domain.functional.dto.request.FormFunctionalRequest
import com.healtheat.api.domain.functional.dto.response.FunctionalResponse
import org.junit.jupiter.api.Assertions.*
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
internal class FunctionalServiceTest(
    @Autowired private val functionalService: FunctionalService,
    @Autowired private val functionalRepository: FunctionalRepository) {

    @Test
    fun save() {
        //given
        val formFunctionalRequest: FormFunctionalRequest = FormFunctionalRequest(
            name = "name",
            deleteStatus = DeleteState.N,
            unit = "unit", // 단위
            dayHighLimit = 1, // 일일섭취량 상한
            dayRowLimit = 1, // 일일섭취량 하한
            mainFunctionality = "mainFunctionality", // 주된 기능성
        )

        //when
        val functionalResponse: FunctionalResponse = functionalService.save(formFunctionalRequest)

        //then
        assertEquals(functionalResponse.name, functionalResponse.name)
    }

    @Test
    fun edit() {
        //given
        val functional = Functional(
            name = "name",
            deleteState = DeleteState.N,
            unit = "unit", // 단위
            dayHighLimit = 1, // 일일섭취량 상한
            dayRowLimit = 1, // 일일섭취량 하한
            mainFunctionality = "mainFunctionality", // 주된 기능성
        )
        functionalRepository.save(functional)
        val formFunctionalRequest: FormFunctionalRequest = FormFunctionalRequest(
            functionalId = functional.functionalId,
            name = "changeName",
            deleteStatus = DeleteState.N,
            unit = "unit", // 단위
            dayHighLimit = 1, // 일일섭취량 상한
            dayRowLimit = 1, // 일일섭취량 하한
            mainFunctionality = "mainFunctionality", // 주된 기능성
        )
        //when
        val functionalResponse: FunctionalResponse = functionalService.edit(formFunctionalRequest)

        //then
        assertEquals(formFunctionalRequest.name, functionalResponse.name)
    }
}
