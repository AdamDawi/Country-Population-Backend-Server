package example.com.models

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val success: Boolean,
    val message: String? = null,
    val countries: List<Country> = emptyList(),
)
