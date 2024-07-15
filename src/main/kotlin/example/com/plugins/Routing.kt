package example.com.plugins

import example.com.routes.getAllCountries
import example.com.routes.root
import io.ktor.application.*
import io.ktor.routing.*

fun Application.configureRouting() {
    routing {
        root()
        getAllCountries()
    }
}
