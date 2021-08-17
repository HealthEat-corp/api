package com.healtheat.api.controller

import com.healtheat.api.RestApiBaseFormat
import com.healtheat.api.controller.dto.request.CreateBrandRequest
import com.healtheat.api.controller.dto.response.BrandResponse
import com.healtheat.api.service.BrandService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class BrandController(@Autowired val brandService: BrandService) {

    @GetMapping("/brands")
    fun findAll(): RestApiBaseFormat {
        return RestApiBaseFormat(data = brandService.findAll())
    }

    @PostMapping("/brand")
    fun save(@RequestBody createBrandRequest: CreateBrandRequest): RestApiBaseFormat {
        return RestApiBaseFormat(data = brandService.save(createBrandRequest))
    }

    // TODO : brand edit
    @PatchMapping("/brand/{id}")
    fun edit(): String {
        return "OK"
    }
}
