package com.healtheat.api.domain.product.repository

import com.healtheat.api.domain.product.ProductFunctionality
import org.springframework.data.jpa.repository.JpaRepository

interface ProductFunctionalityRepository : JpaRepository<ProductFunctionality, Long>{
}
