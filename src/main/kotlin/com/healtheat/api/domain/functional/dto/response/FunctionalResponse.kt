package com.healtheat.api.domain.functional.dto.response

import com.healtheat.api.domain.DeleteState
import com.healtheat.api.domain.functional.Functional

class FunctionalResponse (functional: Functional) {
    val functionalId: Long? = functional.functionalId
    val deleteState: DeleteState = functional.deleteState
    val name: String = functional.name// 기능성 이름
    val unit: String = functional.unit// 단위
    val dayHighLimit: Int = functional.dayHighLimit // 일일섭취량 상한
    val dayRowLimit: Int = functional.dayRowLimit // 일일섭취량 하한
    val mainFunctionality: String = functional.mainFunctionality // 주된 기능성
}
