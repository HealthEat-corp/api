package com.healtheat.api.domain.nutrient

import com.healtheat.api.domain.BaseTimeEntity
import com.healtheat.api.domain.DeleteState
import com.healtheat.api.domain.nutrient.dto.request.FormNutrientRequest
import javax.persistence.*

@Table(name = "nutrient")
@Entity
class Nutrient(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val nutrientId: Long? = null,

    @Enumerated(value = EnumType.STRING)
    var deleteState: DeleteState,

    var name: String, // 영양소 이름
    var unit: String, // 단위
    var dayHighLimit: Int, // 일일섭취량 상한
    var dayRowLimit: Int, // 일일섭취량 하한
    var mainFunctionality: String // 주된기능성
 ) : BaseTimeEntity() {
    fun edit(formNutrientRequest: FormNutrientRequest) {
        this.deleteState = formNutrientRequest.deleteStatus
        this.name = formNutrientRequest.name
        this.unit = formNutrientRequest.unit
        this.dayHighLimit = formNutrientRequest.dayHighLimit
        this.dayRowLimit = formNutrientRequest.dayRowLimit
        this.mainFunctionality = formNutrientRequest.mainFunctionality
    }
 }
