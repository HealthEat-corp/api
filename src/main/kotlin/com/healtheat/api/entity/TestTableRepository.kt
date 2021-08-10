package com.healtheat.api.entity

import com.healtheat.api.entity.TestTable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TestTableRepository: JpaRepository<TestTable, Long> {
}
