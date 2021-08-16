package com.healtheat.api.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BrandController {

    // TODO : brand list
    @GetMapping("/brands")
    fun findAll(): String {
        return "OK"
    }

    // TODO : brand save
    @PostMapping("/brand")
    fun save(): String {
        return "OK"
    }

    // TODO : brand edit
    @PatchMapping("/brand/{id}")
    fun edit(): String {
        return "OK"
    }
}
