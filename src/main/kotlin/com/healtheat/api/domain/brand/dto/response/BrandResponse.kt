package com.healtheat.api.domain.brand.dto.response

import com.healtheat.api.domain.brand.Brand
import com.healtheat.api.domain.DeleteState
import java.time.LocalDateTime

class BrandResponse (brand: Brand) {
    var brandId: Long? = brand.brandId

    var name: String = brand.name

    var status: DeleteState = brand.deleteState

    var createAt: LocalDateTime = brand.createdAt

    var modifiedAt: LocalDateTime = brand.modifiedAt
}
