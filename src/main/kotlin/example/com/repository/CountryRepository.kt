import example.com.models.ApiResponse

interface CountryRepository{
    suspend fun getAllCountries(): ApiResponse
}