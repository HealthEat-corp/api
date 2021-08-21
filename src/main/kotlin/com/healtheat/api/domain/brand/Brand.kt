package com.healtheat.api.domain.brand

import com.healtheat.api.domain.BaseTimeEntity
import com.healtheat.api.domain.DeleteState
import javax.persistence.*

@Entity
class Brand (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val brandId: Long? = null,

    var name: String,

    @Enumerated(EnumType.STRING)
    val deleteState: DeleteState = DeleteState.N
) : BaseTimeEntity() {
    fun changeName(name: String) {
        this.name = name
    }
}
