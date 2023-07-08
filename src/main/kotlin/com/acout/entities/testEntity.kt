package com.acout.entities

import com.acout.entities.UserEntity.primaryKey
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object testEntity : Table<Nothing>(tableName = "test") {
    val id = int(name = "id").primaryKey()
    val test = varchar("test")
}