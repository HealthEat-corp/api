package com.healtheat.api.domain.functional.dto.request

import com.healtheat.api.domain.DeleteState
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

// TODO : validation message 설정 필요
class FormFunctionalRequest(
    val id: Long? = null,

    @field:NotBlank
    val name: String,

    val deleteStatus: DeleteState? = null,

    @field:NotBlank
    val unit: String, // 단위

    @field:Min(0)
    val dayHighLimit: Int, // 일일섭취량 상한

    @field:Min(0)
    val dayRowLimit: Int, // 일일섭취량 하한

    @field:NotBlank
    val mainFunctionality: String, // 주된 기능성
)
