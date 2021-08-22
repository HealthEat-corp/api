package com.healtheat.api.service

import com.healtheat.api.domain.nutrient.Nutrient
import com.healtheat.api.domain.nutrient.NutrientRepository
import com.healtheat.api.domain.nutrient.dto.request.FormNutrientRequest
import com.healtheat.api.domain.nutrient.dto.response.NutrientResponse
import com.healtheat.api.exception.BusinessErrorException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class NutrientService(@Autowired private val nutrientRepository: NutrientRepository) {

    fun findAll(): MutableList<NutrientResponse> {
        val nutrientList: MutableList<Nutrient> = nutrientRepository.findAll()

        val nutrientResponseList: MutableList<NutrientResponse> = mutableListOf()

        nutrientList.forEach{nutrientResponseList.add(NutrientResponse(it))}

        return nutrientResponseList
    }

    @Transactional
    fun save(formNutrientRequest: FormNutrientRequest): NutrientResponse {
        val nutrient = Nutrient(
            deleteState = formNutrientRequest.deleteStatus,
            name = formNutrientRequest.name,
            unit = formNutrientRequest.unit,
            dayHighLimit = formNutrientRequest.dayHighLimit,
            dayRowLimit = formNutrientRequest.dayRowLimit,
            mainFunctionality = formNutrientRequest.mainFunctionality
        )

        nutrientRepository.save(nutrient)

        return NutrientResponse(nutrient)
    }

    @Transactional
    fun edit(formNutrientRequest: FormNutrientRequest): NutrientResponse {
        val nutrient = nutrientRepository.findByIdOrNull(formNutrientRequest.nutrientId) ?: throw BusinessErrorException("잘못된 요청입니다.")

        nutrient.edit(formNutrientRequest)

        return NutrientResponse(nutrient)
    }
}
