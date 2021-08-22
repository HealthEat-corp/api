package com.healtheat.api.domain.nutrient.dto.response

import com.healtheat.api.domain.DeleteState
import com.healtheat.api.domain.nutrient.Nutrient

class NutrientResponse (nutrient: Nutrient) {
    val nutrientId: Long? = nutrient.nutrientId
    val deleteState: DeleteState = nutrient.deleteState
    val name: String = nutrient.name// 기능성 이름
    val unit: String = nutrient.unit// 단위
    val dayHighLimit: Int = nutrient.dayHighLimit // 일일섭취량 상한
    val dayRowLimit: Int = nutrient.dayRowLimit // 일일섭취량 하한
    val mainFunctionality: String = nutrient.mainFunctionality // 주된 기능성
}
