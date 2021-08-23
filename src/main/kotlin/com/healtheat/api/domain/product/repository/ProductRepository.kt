package com.healtheat.api.domain.product.repository

import com.healtheat.api.domain.product.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository: JpaRepository<Product, Long> {
}
