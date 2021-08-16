package com.healtheat.api.domain.product.repository

import com.healtheat.api.domain.product.ProductNutrient
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductNutrientRepository : JpaRepository<ProductNutrient, Long> {
}
