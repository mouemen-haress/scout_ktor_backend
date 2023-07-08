package com.acout.routing

import com.acout.Constant
import com.acout.db.DataBaseConnection
import com.acout.entities.FerkaEntity
import com.acout.entities.OnsorEntity
import com.acout.entities.TaliaaEntity
import com.acout.entities.UserEntity
import com.acout.models.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.ktorm.dsl.*

fun Application.leaderRout() {
    val db = DataBaseConnection.database

    routing {

        post("/getMyAnaser/{ferkaId}") {
            var ferkaId = call.parameters["ferkaId"]?.toInt() ?: -1

            val onsorList = db.from(UserEntity).innerJoin(OnsorEntity, UserEntity.idUser eq OnsorEntity.idOnsor)
                .innerJoin(TaliaaEntity, OnsorEntity.taliaaId eq TaliaaEntity.idTaliaa)
                .innerJoin(FerkaEntity, FerkaEntity.idFerka eq TaliaaEntity.ferkaId).select().where {
                    FerkaEntity.idFerka eq ferkaId
                }.map {
                    var idUser = it[UserEntity.idUser]
                    var serialNb = it[UserEntity.serialNb]
                    var username = it[UserEntity.username]
                    var profile = it[UserEntity.profile]
                    var name = it[UserEntity.name]
                    var dateBearth = it[UserEntity.dateBearth]
                    var bloodType = it[UserEntity.bloodType]
                    var hasClothes = it[UserEntity.hasClothes]
                    var ownNumber = it[UserEntity.ownNumber]
                    var parentNumber = it[UserEntity.parentNumber]
                    var password = it[UserEntity.password]
                    var fatherName = it[UserEntity.fatherName]
                    var fatherWork = it[UserEntity.fatherWork]
                    var motherName = it[UserEntity.motherName]
                    var motherWork = it[UserEntity.motherWork]
                    var placeOfBirth = it[UserEntity.placeOfBirth]
                    var address = it[UserEntity.address]
                    var addressType = it[UserEntity.addressType]
                    var education = it[UserEntity.education]
                    var hobbies = it[UserEntity.hobbies]
                    var insurance = it[UserEntity.insurance]
                    var illness = it[UserEntity.illness]
                    var type = it[UserEntity.type]

                    var currentTaliaa =
                        Taliaa(it[TaliaaEntity.idTaliaa], it[TaliaaEntity.name], it[TaliaaEntity.ferkaId])
                    MyOnsorsListResponse(
                        idUser,
                        serialNb,
                        username,
                        profile,
                        name,
                        dateBearth,
                        bloodType,
                        hasClothes,
                        ownNumber,
                        parentNumber,
                        password,
                        fatherName,
                        fatherWork,
                        motherName,
                        motherWork,
                        placeOfBirth,
                        address,
                        addressType,
                        education,
                        hobbies,
                        insurance,
                        illness,
                        type,
                        currentTaliaa
                    )

                }
            call.respond(Response(success = true, data = onsorList))


        }

        post("/getMyAllTaliaa/{ferkaId}") {
            var ferkaId = call.parameters["ferkaId"]?.toInt() ?: -1

            val taliaaList = db.from(TaliaaEntity).select().where {
                TaliaaEntity.ferkaId eq ferkaId
            }.map {
                var idTaliaa = it[TaliaaEntity.idTaliaa]
                var name = it[TaliaaEntity.name]
                var ferkaId = it[TaliaaEntity.ferkaId]

                Taliaa(idTaliaa, name, ferkaId)
            }
            if (taliaaList != null) {
                call.respond(Response(success = true, data = taliaaList))
            } else {
                call.respond(Response(success = false, data = "", error = Error(Constant.UNKOWN_ERROR)))

            }
        }
    }
}

