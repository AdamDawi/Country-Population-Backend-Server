package example.com.plugins

import example.com.common.Constants.PAGE_NOT_FOUND_MESSAGE
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.configureStatusPages() {
    install(StatusPages){
        status(
            HttpStatusCode.NotFound
        ){call, _->
            call.respond(
                message = PAGE_NOT_FOUND_MESSAGE,
                status = HttpStatusCode.NotFound
            )
        }
    }
}