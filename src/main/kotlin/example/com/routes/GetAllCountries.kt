package example.com.routes

import example.com.repository.CountryRepositoryImpl
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.getAllCountries(){
//    val countryRepository: CountryRepositoryImpl by inject()
    val countryRepository = CountryRepositoryImpl()
    get("/countries"){
        val apiResponse = countryRepository.getAllCountries()
        call.respond(
            message = apiResponse,
            status = HttpStatusCode.OK
        )
    }
}
