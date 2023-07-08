package com.acout.plugins

import com.acout.routing.leaderRout
import com.acout.routing.notesRoutes
import com.acout.routing.userRoutes
import io.ktor.server.application.*

fun Application.configureRouting() {
    notesRoutes()
    userRoutes()
    leaderRout()
}

//@Serializable
//data class Model(val email: String)
//
//routing {
//    get("/") {
//        call.respondText("Hello World!")
//    }
//
//    post("/login") {
//        var m: Model
//        m = call.receive<Model>()
//        println(m.email)
//        call.respond("succus")
//    }
//
//    get("/data") {
//        call.respondText("success", status = HttpStatusCode.Accepted)
//    }
//
//    get("/dataJson") {
//        var responseObj = Model("hi")
//        call.respond(responseObj)
//    }
//
//    get("/header") {
//        call.response.headers.append("keybo", "seynoo")
//    }
//
//    get("/file") {
//        val file = File("files/a.jpeg")
//        call.response.header(
//            HttpHeaders.ContentDisposition,
//            ContentDisposition.Attachment.withParameter(
//                ContentDisposition.Parameters.FileName, "a.jpeg"
//            ).toString()
//        )
//        call.respondFile(file)
//    }
//
//    get("/fileOpen") {
//        val file = File("files/a.jpeg")
//        call.response.header(
//            HttpHeaders.ContentDisposition,
//            ContentDisposition.Inline.withParameter(
//                ContentDisposition.Parameters.FileName, "a.jpeg"
//            ).toString()
//        )
//        call.respondFile(file)
//    }
//
//
//}