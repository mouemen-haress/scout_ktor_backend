package com.acout

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.acout.plugins.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

fun main() {
    embeddedServer(Netty, port = 8081, host = "0.0.0.0", module = Application::module).start(wait = true)
}

fun Application.module() {
    configureSockets()
    configureSerialization()
    configureMonitoring()
    configureSecurity()
    configureRouting()
    install( Authentication){
        jwt{
        }
    }



//    database.insert(NoteEntity) {
//        set(it.name, "mouemen")
//    }

    // select
//    var notes = database.from(NoteEntity).select()
//
//    for (row in notes) {
//        println("bayby starttttt")
//        println(row[NoteEntity.id])
//    }

    //update
//    database.update(NoteEntity) {
//        set(it.name, "ali")
//        where {
//            it.id eq 3
//        }
//    }


    // delete
//    database.delete(NoteEntity) {
//        it.id eq 3
//    }
}
