package com.healtheat.api.domain.product.repository

import com.healtheat.api.domain.product.Brand
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BrandRepository : JpaRepository<Brand, Long> {
}
