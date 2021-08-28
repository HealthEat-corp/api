package com.healtheat.api.domain.product

import com.healtheat.api.domain.BaseTimeEntity
import com.healtheat.api.domain.DeleteState
import com.healtheat.api.domain.brand.Brand
import com.healtheat.api.domain.functional.Functional
import com.healtheat.api.domain.nutrient.Nutrient
import com.healtheat.api.domain.product.dto.request.FormProductRequest
import javax.persistence.*

@Table(name = "product")
@Entity
class Product (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val productId: Long? = null,

    @Enumerated(EnumType.STRING)
    var deleteState: DeleteState, // 사용여부

    var name: String, // 상풍명
    var intakeWay: String, // 섭취 방법
    var shelfLifeMonth: Int, // 유통기한(월)
    var manufacturingNumber: String, //제조번호
    var mainFunctionality: String, //주된 기능성
    var storageWay: String, // 보관 방법
    var licenseNumber: String, //허가번호
    var packingMaterial: String, //포장재질
    var intakePrecaution: String, //섭취주의사항
    var standardSpecification: String, // 기준규격
    var properties: String, //성상
    var shape: String, //형태

    @JoinColumn(name = "brand_id")
    @OneToOne(fetch = FetchType.LAZY)
    var brand: Brand? = null,

    @OneToMany(mappedBy = "product", cascade = [CascadeType.ALL], orphanRemoval = true)
    var productNutrient: MutableList<ProductNutrient> = mutableListOf(),

    @OneToMany(mappedBy = "product", cascade = [CascadeType.ALL])
    var productFunctional: MutableList<ProductFunctional> = mutableListOf()

) : BaseTimeEntity() {
    fun addProductNutrient(nutrients: MutableList<Nutrient>) {
        nutrients.forEach { nutrient ->
            this.productNutrient.add(ProductNutrient(product = this, nutrient = nutrient))
        }
    }

    fun addProductFunctional(functionals: MutableList<Functional>) {
        functionals.forEach { functional ->
            this.productFunctional.add(ProductFunctional(product = this, functional = functional))
        }
    }

    private fun editProductNutrient(nutrients: MutableList<Nutrient>) {
        this.productNutrient.forEach { it.product = null}
        this.productNutrient.clear()

        this.addProductNutrient(nutrients)
    }

    private fun editProductFunctional(functionals: MutableList<Functional>) {
        this.productFunctional.forEach { it.product = null}
        this.productFunctional.clear()

        this.addProductFunctional(functionals)
    }

    fun edit(formProductRequest: FormProductRequest, brand: Brand, nutrients: MutableList<Nutrient>, functionals: MutableList<Functional>) {
        this.deleteState = formProductRequest.deleteState
        this.name = formProductRequest.name
        this.intakeWay = formProductRequest.intakeWay
        this.shelfLifeMonth = formProductRequest.shelfLifeMonth
        this.manufacturingNumber = formProductRequest.manufacturingNumber
        this.mainFunctionality = formProductRequest.mainFunctionality
        this.storageWay = formProductRequest.storageWay
        this.licenseNumber = formProductRequest.licenseNumber
        this.packingMaterial = formProductRequest.packingMaterial
        this.intakePrecaution = formProductRequest.intakePrecaution
        this.standardSpecification = formProductRequest.standardSpecification
        this.properties = formProductRequest.properties
        this.shape = formProductRequest.shape
        this.brand = brand

        this.editProductNutrient(nutrients)
        this.editProductFunctional(functionals)
    }
}
