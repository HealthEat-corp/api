package com.healtheat.api.domain.product.repository

import com.healtheat.api.domain.product.ProductBrand
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductBrandRepository : JpaRepository<ProductBrand, Long> {
}
