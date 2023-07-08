package com.acout.entities

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object LeaderEntity: Table<Nothing>(tableName = "leader") {
    val userId = int(name = "userId").primaryKey()
    val ferkaId = int("ferkaId")

}