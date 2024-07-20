import example.com.models.ApiResponse

interface CountryRepository{
    suspend fun getAllCountries(): ApiResponse
    suspend fun searchCountriesContainingText(searchQuery: String?): ApiResponse
}