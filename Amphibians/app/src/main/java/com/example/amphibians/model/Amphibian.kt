package com.example.amphibians.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Amphibian(
    val name: String,
    val type: AmphibianType,
    val description: String,
    @SerialName("img_src")
    val imageSrc: String,
)

// Just to practice deserialising enums
enum class AmphibianType {
    Toad,
    Frog,
    Salamander,
}
