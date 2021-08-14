package com.healtheat.api.entity

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TestTableRepository: JpaRepository<TestTable, Long> {
}
