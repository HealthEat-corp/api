package com.healtheat.api.service

import com.healtheat.api.domain.DeleteState
import com.healtheat.api.domain.functional.Functional
import com.healtheat.api.domain.functional.FunctionalRepository
import com.healtheat.api.domain.functional.dto.request.FormFunctionalRequest
import com.healtheat.api.domain.functional.dto.response.FunctionalResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class FunctionalService(@Autowired private val functionalRepository: FunctionalRepository) {

    @Transactional
    fun save(formFunctionalRequest: FormFunctionalRequest): FunctionalResponse {
        val functional = Functional(
            deleteState = formFunctionalRequest.deleteStatus ?: DeleteState.N,
            name = formFunctionalRequest.name,
            unit = formFunctionalRequest.unit,
            dayHighLimit = formFunctionalRequest.dayHighLimit,
            dayRowLimit = formFunctionalRequest.dayRowLimit,
            mainFunctionality = formFunctionalRequest.mainFunctionality
        )

        functionalRepository.save(functional)

        return FunctionalResponse(functional)
    }
}
