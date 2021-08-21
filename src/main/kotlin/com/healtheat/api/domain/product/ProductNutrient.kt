package com.healtheat.api.domain.product

import com.healtheat.api.domain.BaseTimeEntity
import com.healtheat.api.domain.nutrient.Nutrient
import javax.persistence.*

@Entity
class ProductNutrient (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @JoinColumn(name = "product_id")
    @ManyToOne(targetEntity = Product::class, fetch = FetchType.LAZY)
    val product: Product,

    @JoinColumn(name = "nutrient_id")
    @ManyToOne(targetEntity = Nutrient::class, fetch = FetchType.LAZY)
    val nutrient: Nutrient
) : BaseTimeEntity()
