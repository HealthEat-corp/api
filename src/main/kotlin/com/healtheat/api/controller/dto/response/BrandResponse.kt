package com.healtheat.api.controller.dto.response

import com.healtheat.api.domain.product.Brand
import com.healtheat.api.domain.product.enum.DeleteState
import java.time.LocalDateTime

class BrandResponse (brand: Brand) {
    var brandId: Long = brand.id

    var name: String = brand.name

    var status: DeleteState = brand.deleteState

    var createAt: LocalDateTime = brand.createdAt

    var modifiedAt: LocalDateTime = brand.modifiedAt
}
