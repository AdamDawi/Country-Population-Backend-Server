import example.com.models.ApiResponse
import example.com.models.Country

interface CountryRepository{
    val countryList: List<Country>
    suspend fun getAllCountries(): ApiResponse
    suspend fun searchCountriesContainingText(searchQuery: String?): ApiResponse
}