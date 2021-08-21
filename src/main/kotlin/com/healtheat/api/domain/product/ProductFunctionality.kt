package com.healtheat.api.domain.product

import com.healtheat.api.domain.BaseTimeEntity
import com.healtheat.api.domain.functional.Functional
import javax.persistence.*

@Entity
class ProductFunctionality (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @JoinColumn(name = "product_id")
    @ManyToOne(targetEntity = Product::class, fetch = FetchType.LAZY)
    val product: Product,

    @JoinColumn(name = "functionality_id")
    @ManyToOne(targetEntity = Functional::class, fetch = FetchType.LAZY)
    val functional: Functional

) : BaseTimeEntity()
