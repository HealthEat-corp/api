package com.healtheat.api.domain.product

import com.healtheat.api.domain.BaseTimeEntity
import com.healtheat.api.domain.product.enum.DeleteState
import javax.persistence.*

@Table(name = "nutrient")
@Entity
class Nutrient(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Enumerated(value = EnumType.STRING)
    val deleteState: DeleteState,

    val nutrientName: String, // 영양소 이름
    val unit: String, // 단위
    val dayHighLimit: String, // 일일섭취량 상한
    val dayRowLimit: String, // 일일섭취량 하한
    val functionalityText: String // 주된기능성
) : BaseTimeEntity()
