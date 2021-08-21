package com.healtheat.api.domain.functional

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FunctionalRepository : JpaRepository<Functional, Long> {
}
