package example.com.plugins

import example.com.routes.getAllCountries
import example.com.routes.root
import example.com.routes.searchCountries
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        root()
        getAllCountries()
        searchCountries()

        staticResources(remotePath = "/images", basePackage = "images")
    }
}
