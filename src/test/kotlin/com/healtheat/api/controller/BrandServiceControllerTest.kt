package com.healtheat.api.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.patch
import org.springframework.test.web.servlet.post

@WebMvcTest(BrandController::class)
class BrandServiceControllerTest(
    @Autowired val brandController: BrandController,
    @Autowired val mockMvc: MockMvc) {

    @Test
    fun findAll() {
        mockMvc.get("/brands").andExpect {
            status { isOk() }
        }
    }

    @Test
    fun save() {
        mockMvc.post("/brand").andExpect {
            status { isOk() }
        }
    }

    @Test
    fun edit() {
        mockMvc.patch("/brand/1").andExpect {
            status { isOk() }
        }
    }
}
