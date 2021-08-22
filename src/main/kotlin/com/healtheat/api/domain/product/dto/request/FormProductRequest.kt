package com.healtheat.api.domain.product.dto.request

import com.healtheat.api.domain.DeleteState
import com.healtheat.api.domain.brand.Brand
import com.healtheat.api.domain.product.Product

// TODO : validation message 설정 필요
data class FormProductRequest(
    val productId: Long? = null,
    val deleteState: DeleteState, // 사용여부
    val name: String, // 상풍명
    val intakeWay: String, // 섭취 방법
    val shelfLifeMonth: Int, // 유통기한(월)
    val manufacturingNumber: String, //제조번호
    val mainFunctionality: String, //주된 기능성
    val storageWay: String, // 보관 방법
    val licenseNumber: String, //허가번호
    val packingMaterial: String, //포장재질
    val intakePrecaution: String, //섭취주의사항
    val standardSpecification: String, // 기준규격
    val properties: String, //성상
    val shape: String, //형태
    val brandId: Long
) {
    fun toEntity(brand: Brand): Product {
        return Product(
            deleteState = this.deleteState,
            name = this.name,
            intakeWay = this.intakeWay,
            shelfLifeMonth = this.shelfLifeMonth,
            manufacturingNumber = this.manufacturingNumber,
            mainFunctionality = this.mainFunctionality,
            storageWay = this.storageWay,
            licenseNumber = this.licenseNumber,
            packingMaterial = this.packingMaterial,
            intakePrecaution = this.intakePrecaution,
            standardSpecification = this.standardSpecification,
            properties = this.properties,
            shape = this.shape,
            brand = brand
        )
    }
}
