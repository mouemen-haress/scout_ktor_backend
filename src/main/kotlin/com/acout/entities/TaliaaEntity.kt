package com.acout.entities

import com.acout.entities.OnsorEntity.primaryKey
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object TaliaaEntity: Table<Nothing>(tableName = "taliaa") {
    val idTaliaa = int(name = "idTaliaa").primaryKey()
    val name = varchar("name")
    val ferkaId = int("ferkaId")

}