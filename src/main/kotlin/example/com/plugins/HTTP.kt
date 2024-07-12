package example.com.plugins

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.request.*
import io.ktor.response.*

fun Application.configureHTTP() {
    install(DefaultHeaders) {
        header("X-Engine", "Ktor") // will send this header with each response
    }
}
