package com.healtheat.api.domain.product

import com.healtheat.api.domain.BaseTimeEntity
import com.healtheat.api.domain.DeleteState
import com.healtheat.api.domain.brand.Brand
import javax.persistence.*

@Table(name = "product")
@Entity
class Product (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val productId: Long? = null,

    @Enumerated(EnumType.STRING)
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

    @JoinColumn(name = "brand_id")
    @OneToOne(fetch = FetchType.LAZY)
    var brand: Brand? = null,

    @OneToMany(mappedBy = "product", cascade = [CascadeType.ALL])
    var productNutrient: MutableList<ProductNutrient> = mutableListOf(),

    @OneToMany(mappedBy = "product", cascade = [CascadeType.ALL])
    var productFunctional: MutableList<ProductFunctional> = mutableListOf()

) : BaseTimeEntity() {
    fun addProductNutrient(productNutrient: ProductNutrient) {
        this.productNutrient.add(productNutrient)
    }

    fun addProductFunctional(productFunctional: ProductFunctional) {
        this.productFunctional.add(productFunctional)
    }
}
