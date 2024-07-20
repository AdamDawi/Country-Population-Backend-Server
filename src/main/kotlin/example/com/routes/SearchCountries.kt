package example.com.routes

import CountryRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.searchCountries(){
        val countryRepository: CountryRepository by inject()
    get("/countries/search"){
        val searchQuery = call.request.queryParameters["name"]

        val apiResponse = countryRepository.searchCountriesContainingText(searchQuery)
        call.respond(
            message = apiResponse,
            status = HttpStatusCode.OK
        )
    }
}