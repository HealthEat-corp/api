package com.healtheat.api.controller

import com.healtheat.api.RestApiBaseFormat
import com.healtheat.api.domain.functional.dto.request.FormFunctionalRequest
import com.healtheat.api.service.FunctionalService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class FunctionalController(@Autowired private val functionalService: FunctionalService) {

    @PostMapping("/functional")
    fun save(@RequestBody formFunctionalRequest: FormFunctionalRequest): RestApiBaseFormat {
        return RestApiBaseFormat(data = functionalService.save(formFunctionalRequest))
    }

    @PatchMapping("/functional/{id}")
    fun save(@PathVariable("id") functionalId: Long, @RequestBody formFunctionalRequest: FormFunctionalRequest): RestApiBaseFormat {
        formFunctionalRequest.changeFunctionalId(functionalId)
        return RestApiBaseFormat(data = functionalService.edit(formFunctionalRequest))
    }
}
