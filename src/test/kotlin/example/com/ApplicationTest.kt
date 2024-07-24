package example.com

import example.com.common.Constants.PAGE_NOT_FOUND_MESSAGE
import example.com.common.Constants.WELCOME_MESSAGE
import example.com.models.ApiResponse
import example.com.models.Country
import example.com.repository.CountryRepositoryImpl
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {

    @Test
    fun root_accessEndpoint_correctMessageResponse() = testApplication {
        val response = client.get("/")

        assertEquals(WELCOME_MESSAGE, response.bodyAsText())
    }
    @Test
    fun root_accessEndpoint_OKStatusResponse() = testApplication{
        val response = client.get("/")

        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun getAllCountries_accessEndpoint_correctCountriesListResponse() = testApplication{
        val countryRepository = CountryRepositoryImpl()

        val response = Json.decodeFromString<ApiResponse>(client.get("/countries").bodyAsText())

        assertEquals(countryRepository.countryList, response.countries)
    }
    @Test
    fun getAllCountries_accessEndpoint_OKStatusResponse() = testApplication{
        val response = client.get("/countries")

        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun nonExistingEndpoint_accessEndpoint_correctMessageResponse() = testApplication{
        val response = client.get("/dsdsa")

        assertEquals(PAGE_NOT_FOUND_MESSAGE, response.bodyAsText())
    }

    @Test
    fun nonExistingEndpoint_accessEndpoint_NotFoundStatusResponse() = testApplication{
        val response = client.get("/dsdsa")

        assertEquals(HttpStatusCode.NotFound, response.status)
    }

    @Test
    fun searchCountries_accessEndpointWithoutParameter_emptyCountryListResponse() = testApplication{
        val response = Json.decodeFromString<ApiResponse>(client.get("/countries/search").bodyAsText())

        assertEquals(emptyList(), response.countries)
    }

    @Test
    fun searchCountries_accessEndpointWithoutParameter_BadRequestStatusResponse() = testApplication{
        val response = client.get("/countries/search")

        assertEquals(HttpStatusCode.BadRequest, response.status)
    }

    @Test
    fun searchCountries_accessEndpointWithNumberParameter_emptyCountryListResponse() = testApplication{
        val response = Json.decodeFromString<ApiResponse>(client.get("/countries/search?name=21").bodyAsText())

        assertEquals(emptyList(), response.countries)
    }

    @Test
    fun searchCountries_accessEndpointWithNumberParameter_BadRequestStatusResponse() = testApplication{
        val response = client.get("/countries/search?name=21")

        assertEquals(HttpStatusCode.BadRequest, response.status)
    }

    @Test
    fun searchCountries_accessEndpointWithParameter_correctSingleCountryResponse() = testApplication{
        val expected = listOf(
            Country("North Korea", 25778816, "/images/KP.png"),
            Country("South Korea", 51269185, "/images/KR.png")
        )
        val response = Json.decodeFromString<ApiResponse>(client.get("/countries/search?name=kor").bodyAsText())

        assertEquals(expected, response.countries)
    }

    @Test
    fun searchCountries_accessEndpointWithParameter_correctMultipleCountryResponse() = testApplication{
        val expected = listOf(Country("Poland", 38386000, "/images/PL.png"))
        val response = Json.decodeFromString<ApiResponse>(client.get("/countries/search?name=pol").bodyAsText())

        assertEquals(expected, response.countries)
    }

    @Test
    fun searchCountries_accessEndpointWithParameter_OKStatusResponse() = testApplication{
        val response = client.get("/countries/search?name=kor")

        assertEquals(HttpStatusCode.OK, response.status)
    }


}
