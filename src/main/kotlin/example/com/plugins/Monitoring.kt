package example.com.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.callloging.*

fun Application.configureMonitoring() {
    install(CallLogging)
}
