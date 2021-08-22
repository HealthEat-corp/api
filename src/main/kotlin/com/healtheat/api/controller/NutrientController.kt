package com.healtheat.api.controller

import com.healtheat.api.RestApiBaseFormat
import com.healtheat.api.domain.nutrient.dto.request.FormNutrientRequest
import com.healtheat.api.service.NutrientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class NutrientController(@Autowired private val nutrientService: NutrientService) {

    @GetMapping("/nutrient")
    fun findAll(): RestApiBaseFormat {
        return RestApiBaseFormat(data = nutrientService.findAll())
    }

    @PostMapping("/nutrient")
    fun save(@RequestBody formNutrientRequest: FormNutrientRequest): RestApiBaseFormat {
        return RestApiBaseFormat(data = nutrientService.save(formNutrientRequest))
    }

    @PatchMapping("/nutrient/{id}")
    fun save(@PathVariable("id") nutrientId: Long, @RequestBody formNutrientRequest: FormNutrientRequest): RestApiBaseFormat {
        formNutrientRequest.changeNutrientId(nutrientId)
        return RestApiBaseFormat(data = nutrientService.edit(formNutrientRequest))
    }
}
