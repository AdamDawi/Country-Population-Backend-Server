package example.com.models

import kotlinx.serialization.Serializable

@Serializable
data class Country(
    val name: String,
    val population: Int,
    val image: String
)