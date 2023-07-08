package com.acout.routing

import com.acout.Constant
import com.acout.db.DataBaseConnection
import com.acout.entities.OnsorEntity
import com.acout.entities.TaliaaEntity
import com.acout.entities.UserEntity
import com.acout.models.*
import com.acout.utils.PasswordHelper
import com.acout.utils.Tools
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.ktorm.dsl.*
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

fun Application.notesRoutes() {
    val db = DataBaseConnection.database

    routing {

        post("/insertOnsor") {
            val request = call.receive<InsertOnsorRequest>()

            var newId = db.insertAndGenerateKey(UserEntity) {
                set(it.serialNb, request.serialNb)
                set(it.username, request.username)
                set(it.profile, request.profile)
                set(it.name, request.name)
                set(it.dateBearth, request.dateBearth)
                set(it.bloodType, request.bloodType)
                set(it.hasClothes, request.hasClothes)
                set(it.ownNumber, request.ownNumber)
                set(it.parentNumber, request.parentNumber)
                set(it.password, request.password)
                set(it.fatherName, request.fatherName)
                set(it.fatherWork, request.fatherWork)
                set(it.motherName, request.motherName)
                set(it.motherWork, request.motherWork)
                set(it.placeOfBirth, request.placeOfBirth)
                set(it.address, request.address)
                set(it.addressType, request.addressType)
                set(it.education, request.education)
                set(it.hobbies, request.hobbies)
                set(it.insurance, request.insurance)
                set(it.illness, request.illness)
                set(it.type, request.type)
            }

            if (newId != null) {
                var insertIntoOnsorTableResult = db.insert(OnsorEntity) {
                    set(it.taliaaId, request.taliaaId)
                    set(it.idOnsor, newId)

                }
                if (insertIntoOnsorTableResult == 1) {
                    call.respond("siii")
                    call.respond(
                        HttpStatusCode.OK, Response(
                            success = true,
                            data = "Onsor has been added"
                        )
                    )
                } else {
                    call.respond(
                        HttpStatusCode.BadRequest, "siiiii"
                    )
                }
            } else {
                call.respond(
                    HttpStatusCode.BadRequest, "siiii"
                )

            }

        }

        post("/insertOnsor/firstStep") {
            val request = call.receive<InsertOnsorFirstStepRequest>()
            var generatedSerialNb = PasswordHelper.getRandomPassWord(8)
            var generatedPassword = PasswordHelper.getRandomPassWord(8)
            var checkUser = db.from(UserEntity).select().where() {
                UserEntity.username eq request.username
            }
            if(checkUser!=null){
                call.respond(
                    Response(
                        data = "",
                        error = com.acout.models.Error(message = Constant.USER_EXIST),
                        success = false
                    )
                )
                return@post
            }
            var newId = db.insertAndGenerateKey(UserEntity) {
                set(it.username, request.username)
                set(it.name, request.name)
                set(it.dateBearth, request.dateBearth)
                set(it.bloodType, request.bloodType)
                set(it.hasClothes, request.hasClothes)
                set(it.ownNumber, request.ownNumber)
                set(it.serialNb, generatedSerialNb)
                set(it.password, generatedSerialNb)
            }

            var insertedId = Tools.convertToInt(newId)

            if (insertedId != null) {
                var insertIntoOnsorTableResult = db.insert(OnsorEntity) {
                    set(it.taliaaId, request.taliaaId)
                    set(it.idOnsor, insertedId)

                }
                if (insertIntoOnsorTableResult == 1) {
                    var addedOnsor = Users(
                        username = request.username,
                        name = request.name,
                        dateBearth = request.dateBearth,
                        idUser = insertedId,
                        bloodType = request.bloodType,
                        hasClothes = request.hasClothes,
                        ownNumber = request.ownNumber,
                        serialNb = generatedSerialNb,
                        password = generatedPassword
                    )
                    call.respond(
                        HttpStatusCode.OK, Response(
                            success = true,
                            data = addedOnsor
                        )
                    )
                } else {
                    var parsedId = Tools.parseToInt(newId)!!

                    db.delete(UserEntity) {
                        it.idUser eq parsedId
                    }
                }
                call.respond(
                    Response(
                        data = "",
                        error = com.acout.models.Error(message = Constant.FAILURE),
                        success = false
                    )
                )
            } else {
                call.respond(
                    Response(
                        data = "",
                        error = com.acout.models.Error(message = Constant.FAILURE),
                        success = false
                    )
                )

            }


        }

        post("/uploadProfile/{id}") {
            var onsorId = call.parameters["id"]?.toInt() ?: -1
            if (onsorId == null) {
                call.respond(Response(data = "", Error(message = Constant.FAILURE), success = false))
                return@post
            }
            val multipart = call.receiveMultipart()
            var fileName: String? = null
            multipart.readAllParts().forEach { part ->
                when (part) {
                    is PartData.FileItem -> {
                        fileName = saveFile(part)
                    }

                    else -> {}
                }
                part.dispose()
            }
            var rowCount = db.update(UserEntity) {
                set(it.profile, fileName)
                where {
                    it.idUser eq onsorId
                }
            }
            if (rowCount > 0) {
                call.respond(Response(data = "Uploaded successfully", success = true))
            } else {
                call.respond(Response(data = "", Error(message = Constant.FAILURE), success = false))

            }
        }
    }


}


fun saveFile(part: PartData.FileItem): String {
    val originalFileName = part.originalFileName ?: throw IllegalArgumentException("Missing file name")
    val fileExtension = File(originalFileName).extension
    val targetDirectory = "uploads"
    val uniqueFileName = "${UUID.randomUUID()}.$fileExtension"
    val targetFile = File("$targetDirectory/$uniqueFileName")

    if (!Files.exists(Paths.get(targetDirectory))) {
        Files.createDirectory(Paths.get(targetDirectory))
    }

    part.streamProvider().use { input ->
        targetFile.outputStream().buffered().use { output ->
            input.copyTo(output)
        }
    }

    val baseUrl = "http://192.168.1.111:8081" // Replace with the base URL of your server
    val imageUrl = "$baseUrl/$targetDirectory/$uniqueFileName"
    return imageUrl
}
