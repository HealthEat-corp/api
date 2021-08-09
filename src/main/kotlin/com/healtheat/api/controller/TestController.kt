package com.healtheat.api.controller

import com.healtheat.api.entity.TestTable
import com.healtheat.api.entity.TestTableRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController(
    private val testTableRepository: TestTableRepository) {

    @GetMapping("/test")
    fun test(): MutableList<TestTable> {
        val findAll = testTableRepository.findAll();
        return findAll;
    }
}
