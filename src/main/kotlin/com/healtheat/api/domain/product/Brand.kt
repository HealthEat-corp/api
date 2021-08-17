package com.healtheat.api.domain.product

import com.healtheat.api.domain.BaseTimeEntity
import com.healtheat.api.domain.product.enum.DeleteState
import javax.persistence.*

@Entity
class Brand (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val name: String,

    @Enumerated(EnumType.STRING)
    val deleteState: DeleteState = DeleteState.N
) : BaseTimeEntity()
