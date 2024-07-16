package example.com

import example.com.plugins.*
import io.ktor.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
//    configureKoin()
    configureDefaultHeader()
    configureSerialization()
    configureMonitoring()
    configureRouting()
}
