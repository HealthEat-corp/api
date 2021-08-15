package com.healtheat.api.domain.product

import com.healtheat.api.domain.BaseTimeEntity
import com.healtheat.api.domain.product.enum.DeleteState
import javax.persistence.*

@Table(name = "product")
@Entity
class Product (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val productName: String, // 상풍명

    @Enumerated(EnumType.STRING)
    val deleteState: DeleteState? = DeleteState.N, // 사용여부

    val intakeWay: String, // 섭취 방법
    val shelfLifeMonth: Int, // 유통기한(월)
    val manufacturingNumber: String, //제조번호
    val functionalityText: String, //주된 기능성
    val storageWay: String, // 보관 방법
    val licenseNumber: String, //허가번호
    val packingMaterial: String, //포장재질
    val intakePrecaution: String, //섭취주의사항
    val standardSpecification: String, // 기준규격
    val properties: String, //성상
    val shape: String, //형태

    @JoinColumn(name = "productBrand_id")
    @ManyToOne(targetEntity = ProductBrand::class, fetch = FetchType.LAZY)
    var productBrand: ProductBrand
    // TODO : FK  설정필요
//    var nutrientId: Long // FK
//    var functionalityId: Long // FK
) : BaseTimeEntity()
