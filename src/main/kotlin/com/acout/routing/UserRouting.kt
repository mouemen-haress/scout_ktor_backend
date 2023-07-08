package com.acout.routing

import com.acout.Constant
import com.acout.db.DataBaseConnection
import com.acout.entities.*
import com.acout.models.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.ktorm.dsl.*
import java.io.File


fun Application.userRoutes() {
    val db = DataBaseConnection.database

    routing {

        post("/login") { p ->
            var user = call.receive<AuthUserRequest>()
//            val tokenManager = TokenManager(HoconApplicationConfig(ConfigFactory.load()))

            val userCheck = db.from(UserEntity).select(UserEntity.type, UserEntity.password).where {
                    UserEntity.username eq user.username
                }.map {
                    var type = it[UserEntity.type]
                    var password = it[UserEntity.password]
                    Users(type = type, password = password)
                }.firstOrNull()

            if (userCheck != null) {
                if (userCheck.password.equals(user.password)) {
                    if (userCheck.type.equals(Constant.LEADER)) {
                        val leader =
                            db.from(UserEntity).innerJoin(LeaderEntity, UserEntity.idUser eq LeaderEntity.userId)
                                .innerJoin(FerkaEntity, FerkaEntity.idFerka eq LeaderEntity.ferkaId).select().map {
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

                                    var ferka =
                                        Ferka(it[FerkaEntity.idFerka], it[FerkaEntity.name], it[FerkaEntity.fawjeId])
                                    LeaderResponse(
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
                                        ferka
                                    )

                                }.firstOrNull()

                        if (leader != null) {
                            call.respond(Response(success = true, data = leader))
                            return@post
                        }
                    }

                } else {
                    call.respond(
                        Response(
                            success = false, data = "", error = Error("يبدو أن كلمة السر المدخلة خاطئة")
                        )
                    )
                    return@post
                }
            }

            call.respond(Response(success = false, data = "", error = Error("لا يوجد حساب ب هذا الأسم !!")))
        }

        get("/file/{name}") {
            var ferkaId = call.parameters["name"]?.toString() ?: ""
            var file = File("uploads/$ferkaId")
//            call.response.header(HttpHeaders.ContentDisposition,
//                ContentDisposition.Attachment.withParameter(
//                    ContentDisposition.Parameters.FileName,"downloadableImage.jpeg"
//                ).toString()
//            )
            call.respondFile(file)
        }
    }


}




