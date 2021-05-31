package com.example.covidtracker.model

data class Superhero(
    val id: String? = null,
    val name: String? = null,
    val powerstats: powerState? = null,
    val appearance: appearance? = null,
    val biography: biography? = null,
    val work: work? = null,
    val connections: connections? = null,
    val images: images? = null,
    val image: image? = null
)
