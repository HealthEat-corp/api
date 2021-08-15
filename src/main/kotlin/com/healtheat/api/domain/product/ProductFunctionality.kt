package com.healtheat.api.domain.product

import com.healtheat.api.domain.BaseTimeEntity
import com.healtheat.api.domain.product.enum.DeleteState
import javax.persistence.*

@Entity
class ProductFunctionality(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Enumerated(EnumType.STRING)
    val deleteState: DeleteState, // 삭제여부

    val functionalityName: String, // 기능성 이름
    val unit: String, // 단위
    val dayHighLimit: String, // 일일섭취량 상한
    val dayRowLimit: String, // 일일섭취량 하한
    val functionalityText: String // 추가 정보
) : BaseTimeEntity()
