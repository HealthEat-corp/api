package com.healtheat.api.domain.brand.dto.request

data class EditBrandRequest(
    var brandId: Long,
    var name: String) {

    fun changeId(brandId: Long) {
        this.brandId = brandId;
    }
}
