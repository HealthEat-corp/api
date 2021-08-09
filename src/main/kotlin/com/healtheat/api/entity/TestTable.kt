package com.healtheat.api.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Table(name="test_table")
@Entity
class TestTable(name: String) {
    @Id
    @GeneratedValue
    var id: Long? = null
    var name: String = name
}
