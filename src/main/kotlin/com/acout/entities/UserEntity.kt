package com.acout.entities

import org.ktorm.schema.Table
import org.ktorm.schema.boolean
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object UserEntity : Table<Nothing>(tableName = "users") {
    val idUser = int(name = "idUser").primaryKey()
    val serialNb = varchar("serialNb")
    val username = varchar("username")
    val profile = varchar("profile")
    val name = varchar("name")
    val dateBearth = varchar("dateBearth")
    val bloodType = varchar("bloodType")
    val hasClothes = boolean("hasClothes")
    val ownNumber = varchar("ownNumber")
    val parentNumber = varchar("parentNumber")
    val password = varchar("password")
    val fatherName = varchar("fatherName")
    val fatherWork = varchar("fatherWork")
    val motherName = varchar("motherName")
    val motherWork = varchar("motherWork")
    val placeOfBirth = varchar("placeOfBirth")
    val address = varchar("address")
    val addressType = varchar("addressType")
    val education = varchar("education")
    val hobbies = varchar("hobbies")
    val insurance = varchar("insurance")
    val illness = varchar("illness")
    val type = varchar("type")
    val familtNb = int(name = "familtNb")


}