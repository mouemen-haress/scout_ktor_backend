package com.acout.models

import com.acout.entities.UserEntity.primaryKey
import kotlinx.serialization.Serializable
import org.ktorm.schema.int
import org.ktorm.schema.varchar

@Serializable
data class InsertOnsorRequest(
    val taliaaId: Int,
    val serialNb: String,
    val username: String,
    val profile: String,
    val name: String,
    val dateBearth: String,
    val bloodType: String,
    val hasClothes: String,
    val ownNumber: String,
    val parentNumber: String,
    val password: String,
    val fatherName: String,
    val fatherWork: String,
    val motherName: String,
    val motherWork: String,
    val placeOfBirth: String,
    val address: String,
    val addressType: String,
    val education: String,
    val hobbies: String,
    val insurance: String,
    val illness: String,
    val type: String
)
