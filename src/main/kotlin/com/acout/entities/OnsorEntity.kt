package com.acout.entities

import org.ktorm.schema.Table
import org.ktorm.schema.int

object OnsorEntity: Table<Nothing>(tableName = "onsor") {
    val idOnsor = int(name = "idOnsor").primaryKey()
    val taliaaId = int("taliaaId")
}