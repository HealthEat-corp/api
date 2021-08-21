package com.healtheat.api.domain.functional

import com.healtheat.api.domain.BaseTimeEntity
import com.healtheat.api.domain.DeleteState
import com.healtheat.api.domain.functional.dto.request.FormFunctionalRequest
import javax.persistence.*

@Table(name = "functional")
@Entity
class Functional(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val functionalId: Long? = null,

    @Enumerated(EnumType.STRING)
    var deleteState: DeleteState, // 삭제여부
    var name: String, // 기능성 이름
    var unit: String, // 단위
    var dayHighLimit: Int, // 일일섭취량 상한
    var dayRowLimit: Int, // 일일섭취량 하한
    var mainFunctionality: String, // 주된 기능성

) : BaseTimeEntity() {

    fun edit(formFunctionalRequest: FormFunctionalRequest) {
        this.deleteState = formFunctionalRequest.deleteStatus
        this.name = formFunctionalRequest.name
        this.unit = formFunctionalRequest.unit
        this.dayHighLimit = formFunctionalRequest.dayHighLimit
        this.dayRowLimit = formFunctionalRequest.dayRowLimit
        this.mainFunctionality = formFunctionalRequest.mainFunctionality
    }
}
