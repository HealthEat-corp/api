package com.healtheat.api.domain.product.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductNutrientRepository : JpaRepository<ProductNutrientRepository, Long> {
}
