package com.healtheat.api.domain.repository

import com.healtheat.api.config.DataSourceConfig
import com.healtheat.api.domain.product.Product
import com.healtheat.api.domain.product.repository.ProductRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles(value = ["local"])
@Import(DataSourceConfig::class)
class ProductRepositoryTest {

    @Autowired
    lateinit var productRepository: ProductRepository

    @Test
    fun save() {
        val product: Product = Product(
            productName = "productName",
            deleteState = "N",
            intakeWay = "섭취방법",
            shelfLifeMonth = 5,
            manufacturingNumber = "제조번호",
            functionalityText = "주된 기능성",
            storageWay = "보관 방법",
            licenseNumber = "허가번호",
            packingMaterial = "포장재질",
            intakePrecaution = "섭취주의사항",
            standardSpecification = "기준규격",
            properties = "성상",
            shape = "형태",
            createdAt = LocalDateTime.now(),
            modifiedAt = LocalDateTime.now()
        )

        productRepository.save(product);

        val products: List<Product> = productRepository.findAll()

        Assertions.assertThat(products[products.lastIndex].productName).isEqualTo(product.productName);
    }
}
