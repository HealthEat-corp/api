package com.healtheat.api.domain.nutrient

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface NutrientRepository : JpaRepository<Nutrient, Long> {

    fun findByNutrientIdIn(nutrientIds: MutableList<Long>): MutableList<Nutrient>
}
