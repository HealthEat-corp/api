package com.healtheat.api.domain.functional

import com.healtheat.api.domain.BaseTimeEntity
import com.healtheat.api.domain.DeleteState
import javax.persistence.*

@Table(name = "functional")
@Entity
class Functional(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Enumerated(EnumType.STRING)
    val deleteState: DeleteState, // 삭제여부
    val name: String, // 기능성 이름
    val unit: String, // 단위
    val dayHighLimit: Int, // 일일섭취량 상한
    val dayRowLimit: Int, // 일일섭취량 하한
    val mainFunctionality: String, // 주된 기능성

) : BaseTimeEntity()
