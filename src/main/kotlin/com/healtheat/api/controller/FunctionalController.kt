package com.healtheat.api.controller

import com.healtheat.api.RestApiBaseFormat
import com.healtheat.api.domain.functional.dto.request.FormFunctionalRequest
import com.healtheat.api.service.FunctionalService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class FunctionalController(@Autowired private val functionalService: FunctionalService) {

    @PostMapping("/functional")
    fun save(@RequestBody formFunctionalRequest: FormFunctionalRequest): RestApiBaseFormat {
        return RestApiBaseFormat(data = functionalService.save(formFunctionalRequest))
    }
}
