package com.acout.models

import com.acout.entities.UserEntity.primaryKey
import kotlinx.serialization.Serializable
import org.ktorm.schema.int
import org.ktorm.schema.varchar

@Serializable
data class InsertOnsorFirstStepRequest(
    val taliaaId: Int,
    val username: String,
    val name: String,
    val dateBearth: String,
    val bloodType: String,
    val hasClothes: Boolean,
    val ownNumber: String,


)
