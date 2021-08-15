package com.healtheat.api.domain.product.repository

import com.healtheat.api.domain.product.Functionality
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FunctionalityRepository : JpaRepository<Functionality, Long> {
}
