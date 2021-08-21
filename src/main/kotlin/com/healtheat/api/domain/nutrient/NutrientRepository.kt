package com.healtheat.api.domain.nutrient

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface NutrientRepository : JpaRepository<Nutrient, Long> {
}
