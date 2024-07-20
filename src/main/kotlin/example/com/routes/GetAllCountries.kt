package example.com.routes

import CountryRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.getAllCountries(){
    val countryRepository: CountryRepository by inject()
    get("/countries"){
        val apiResponse = countryRepository.getAllCountries()
        call.respond(
            message = apiResponse,
            status = HttpStatusCode.OK
        )
    }
}
