package com.healtheat.api.domain.product.repository

import com.healtheat.api.domain.product.ProductFunctional
import org.springframework.data.jpa.repository.JpaRepository

interface ProductFunctionalityRepository : JpaRepository<ProductFunctional, Long>{
}
