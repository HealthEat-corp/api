package com.healtheat.api.domain.product.dto.response

import com.healtheat.api.domain.DeleteState
import com.healtheat.api.domain.brand.Brand
import com.healtheat.api.domain.product.Product
import java.time.LocalDateTime

class ProductResponse (product: Product) {
    val productId: Long = product.productId!!
    val deleteState: DeleteState = product.deleteState// 사용여부
    val name: String = product.name// 상풍명
    val intakeWay: String = product.intakeWay// 섭취 방법
    val shelfLifeMonth: Int = product.shelfLifeMonth// 유통기한(월)
    val manufacturingNumber: String = product.manufacturingNumber//제조번호
    val mainFunctionality: String = product.mainFunctionality//주된 기능성
    val storageWay: String = product.storageWay// 보관 방법
    val licenseNumber: String = product.licenseNumber//허가번호
    val packingMaterial: String = product.packingMaterial//포장재질
    val intakePrecaution: String = product.intakePrecaution//섭취주의사항
    val standardSpecification: String = product.standardSpecification// 기준규격
    val properties: String = product.properties//성상
    val shape: String = product.shape//형태
    var brand: Brand = product.brand!!
    var nutrientName: MutableList<String> = mutableListOf()
    var functionalName: MutableList<String> = mutableListOf()
    val modifiedAt: LocalDateTime = product.modifiedAt
    val createAt: LocalDateTime = product.createdAt

    init {
        product.productNutrient.forEach { this.nutrientName.add(it.nutrient.name) }
        product.productFunctional.forEach { this.functionalName.add(it.functional.name) }
    }
}
