package example.com

import CountryRepository
import example.com.common.Constants.PAGE_NOT_FOUND_MESSAGE
import example.com.common.Constants.WELCOME_MESSAGE
import example.com.models.ApiResponse
import example.com.models.Country
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.testing.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.koin.java.KoinJavaComponent.inject
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {

    private val countryRepository: CountryRepository by inject(CountryRepository::class.java)

    @Test
    fun root_accessEndpoint_correctMessageResponse() {
        withTestApplication(moduleFunction = Application::module) {
            handleRequest(HttpMethod.Get, "/").apply {
                assertEquals(WELCOME_MESSAGE, response.content)
            }
        }
    }
    @Test
    fun root_accessEndpoint_OKStatusResponse() {
        withTestApplication(moduleFunction = Application::module) {
            handleRequest(HttpMethod.Get, "/").apply {
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    @Test
    fun getAllCountries_accessEndpoint_correctCountriesListResponse() {
        withTestApplication(moduleFunction = Application::module) {
            handleRequest(HttpMethod.Get, "/countries").apply {
                val response = Json.decodeFromString<ApiResponse>(response.content.toString())
                assertEquals(countryRepository.countryList, response.countries)
            }
        }
    }
    @Test
    fun getAllCountries_accessEndpoint_OKStatusResponse() {
        withTestApplication(moduleFunction = Application::module) {
            handleRequest(HttpMethod.Get, "/countries").apply {
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    @Test
    fun nonExistingEndpoint_accessEndpoint_correctMessageResponse() {
        withTestApplication(moduleFunction = Application::module) {
            handleRequest(HttpMethod.Get, "/dsdsa").apply {
                assertEquals(PAGE_NOT_FOUND_MESSAGE, response.content)
            }
        }
    }

    @Test
    fun nonExistingEndpoint_accessEndpoint_NotFoundStatusResponse() {
        withTestApplication(moduleFunction = Application::module) {
            handleRequest(HttpMethod.Get, "/dsdsa").apply {
                assertEquals(HttpStatusCode.NotFound, response.status())
            }
        }
    }

    @Test
    fun searchCountries_accessEndpointWithoutParameter_emptyCountryListResponse() {
        withTestApplication(moduleFunction = Application::module) {
            handleRequest(HttpMethod.Get, "/countries/search").apply {
                val response = Json.decodeFromString<ApiResponse>(response.content.toString())
                assertEquals(emptyList(), response.countries)
            }
        }
    }

    @Test
    fun searchCountries_accessEndpointWithoutParameter_BadRequestStatusResponse() {
        withTestApplication(moduleFunction = Application::module) {
            handleRequest(HttpMethod.Get, "/countries/search").apply {
                assertEquals(HttpStatusCode.BadRequest, response.status())
            }
        }
    }

    @Test
    fun searchCountries_accessEndpointWithNumberParameter_emptyCountryListResponse() {
        withTestApplication(moduleFunction = Application::module) {
            handleRequest(HttpMethod.Get, "/countries/search?name=21").apply {
                val response = Json.decodeFromString<ApiResponse>(response.content.toString())
                assertEquals(emptyList(), response.countries)
            }
        }
    }

    @Test
    fun searchCountries_accessEndpointWithNumberParameter_BadRequestStatusResponse() {
        withTestApplication(moduleFunction = Application::module) {
            handleRequest(HttpMethod.Get, "/countries/search?name=21").apply {
                assertEquals(HttpStatusCode.BadRequest, response.status())
            }
        }
    }

    @Test
    fun searchCountries_accessEndpointWithParameter_correctSingleCountryResponse() {
        val expected = listOf(
            Country("North Korea", 25778816, "/images/KP.png"),
            Country("South Korea", 51269185, "/images/KR.png")
        )
        withTestApplication(moduleFunction = Application::module) {
            handleRequest(HttpMethod.Get, "/countries/search?name=kor").apply {
                val response = Json.decodeFromString<ApiResponse>(response.content.toString())
                assertEquals(expected, response.countries)
            }
        }
    }

    @Test
    fun searchCountries_accessEndpointWithParameter_OKStatusResponse() {
        withTestApplication(moduleFunction = Application::module) {
            handleRequest(HttpMethod.Get, "/countries/search?name=kor").apply {
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    @Test
    fun searchCountries_accessEndpointWithParameter_correctMultipleCountryResponse() {
        val expected = listOf(Country("Poland", 38386000, "/images/PL.png"))
        withTestApplication(moduleFunction = Application::module) {
            handleRequest(HttpMethod.Get, "/countries/search?name=pol").apply {
                val response = Json.decodeFromString<ApiResponse>(response.content.toString())
                assertEquals(expected, response.countries)
            }
        }
    }
}
