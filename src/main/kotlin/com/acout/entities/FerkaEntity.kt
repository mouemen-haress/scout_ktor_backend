package com.acout.entities

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object FerkaEntity: Table<Nothing>(tableName = "ferka") {
    val idFerka = int(name = "idFerka").primaryKey()
    val name = varchar("name")
    val fawjeId = int("fawjeId")

}