package com.healtheat.api.controller

import com.healtheat.api.RestApiBaseFormat
import com.healtheat.api.domain.brand.dto.request.CreateBrandRequest
import com.healtheat.api.domain.brand.dto.request.EditBrandRequest
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

    @PatchMapping("/brand/{id}")
    fun edit(@PathVariable(value = "id") brandId: Long,
             @RequestBody editBrandRequest: EditBrandRequest): RestApiBaseFormat {
        editBrandRequest.changeId(brandId)
        return RestApiBaseFormat(data = brandService.edit(editBrandRequest))
    }
}
