package com.healtheat.api.controller

import com.healtheat.api.entity.TestTableRepository
import com.healtheat.api.entity.TestTable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController(
    private val testTableRepository: TestTableRepository
) {

    @GetMapping("/test")
    fun test(): MutableList<TestTable> {
        val findAll = testTableRepository.findAll();
        return findAll;
    }

    @GetMapping("/save")
    fun save(): String {
        val testTable = TestTable(2, "test2")

        testTableRepository.save(testTable)

        return "OK"
    }
}
