package com.healtheat.api.domain.product.repository

import com.healtheat.api.domain.product.ProductNutrient
import org.springframework.data.jpa.repository.JpaRepository

interface ProductNutrientRepository : JpaRepository<ProductNutrient, Long> {
}
