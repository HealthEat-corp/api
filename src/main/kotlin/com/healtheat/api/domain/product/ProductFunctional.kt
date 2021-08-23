package com.healtheat.api.domain.product

import com.healtheat.api.domain.BaseTimeEntity
import com.healtheat.api.domain.functional.Functional
import javax.persistence.*

@Table(name = "product_functional")
@Entity
class ProductFunctional (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val productFunctionalId: Long? = null,

    @JoinColumn(name = "product_id")
    @ManyToOne(fetch = FetchType.LAZY)
    val product: Product,

    @JoinColumn(name = "functionality_id")
    @ManyToOne(fetch = FetchType.LAZY)
    val functional: Functional

) : BaseTimeEntity()
