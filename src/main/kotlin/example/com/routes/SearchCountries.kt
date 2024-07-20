package example.com.routes

import example.com.repository.CountryRepositoryImpl
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.searchCountries(){
    //    val countryRepository: CountryRepositoryImpl by inject()
    val countryRepository = CountryRepositoryImpl()
    get("/countries/search"){
        val searchQuery = call.request.queryParameters["name"]

        val apiResponse = countryRepository.searchCountriesContainingText(searchQuery)
        call.respond(
            message = apiResponse,
            status = HttpStatusCode.OK
        )
    }
}