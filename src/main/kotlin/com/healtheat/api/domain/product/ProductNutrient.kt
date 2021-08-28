package com.healtheat.api.domain.product

import com.healtheat.api.domain.BaseTimeEntity
import com.healtheat.api.domain.nutrient.Nutrient
import javax.persistence.*

@Entity
class ProductNutrient (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val productNutrientId: Long? = null,

    @JoinColumn(name = "product_id")
    @ManyToOne(fetch = FetchType.LAZY)
    var product: Product?,

    @JoinColumn(name = "nutrient_id")
    @ManyToOne(fetch = FetchType.LAZY)
    val nutrient: Nutrient
) : BaseTimeEntity()
