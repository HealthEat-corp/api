package com.healtheat.api.domain.product

import com.healtheat.api.domain.product.enum.DeleteState
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Table(name = "product")
@Entity
class Product (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val productName: String, // 상풍명

    // TODO : ENUM 수정 필요
    val deleteState: String, // 사용여부
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
    val createdAt: LocalDateTime, // 생성 날짜
    val modifiedAt: LocalDateTime //수정 날짜

    // TODO : FK  설정필요
//    var productBrandId: Long // FK
//    var nutrientId: Long // FK
//    var functionalityId: Long // FK
)
