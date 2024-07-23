package example.com.plugins

import example.com.common.Constants.PAGE_NOT_FOUND_MESSAGE
import example.com.models.ApiResponse
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
        exception<IllegalArgumentException>{ call, exception ->
            call.respond(
                message = ApiResponse(
                    success = false,
                    message = exception.message.toString()
                ),
                status = HttpStatusCode.BadRequest
            )
        }
    }
}