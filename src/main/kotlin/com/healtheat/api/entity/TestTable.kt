package com.healtheat.api.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Table(name="test_table")
@Entity
class TestTable(id: Long, name: String) {
    @Id
    var id: Long = id

    var name: String = name
}
