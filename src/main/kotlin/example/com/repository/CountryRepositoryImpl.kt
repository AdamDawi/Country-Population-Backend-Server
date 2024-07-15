package example.com.repository

import CountryRepository
import example.com.models.ApiResponse

class CountryRepositoryImpl: CountryRepository {
    override suspend fun getAllCountries(): ApiResponse {
        TODO("Not yet implemented")
    }

}