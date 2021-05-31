package com.example.covidtracker.model

data class appearance(
    val gender: String? = null,
    val race: String? = null,
    val height: List<String>? = null,
    val weight: List<String>? = null,
    val eyeColor: String? = null,
    val hairColor: String? = null
)
