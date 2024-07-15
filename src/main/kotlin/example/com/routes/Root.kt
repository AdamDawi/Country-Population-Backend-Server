package example.com.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.root(){
    get("/"){
        call.respond(
            message = "Welcome to the Country Population API",
            status = HttpStatusCode.OK
        )
    }
}