package com.acout.models

import kotlinx.serialization.Serializable

@Serializable
data class LeaderResponse
    (
    var idUser: Int?,
    var serialNb: String?,
    var username: String?,
    var profile: String?,
    var name: String?,
    var dateBearth: String?,
    var bloodType: String?,
    var hasClothes: Boolean?,
    var ownNumber: String?,
    var parentNumber: String?,
    var password: String?,
    var fatherName: String?,
    var fatherWork: String?,
    var motherName: String?,
    var motherWork: String?,
    var placeOfBirth: String?,
    var address: String?,
    var addressType: String?,
    var education: String?,
    var hobbies: String?,
    var insurance: String?,
    var illness: String?,
    var type: String?,
    var ferka: Ferka?
)
