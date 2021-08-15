package com.healtheat.api.domain.product.repository

import com.healtheat.api.domain.product.Nutrient
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface NutrientRepository : JpaRepository<Nutrient, Long> {
}
