package com.example.covidtracker.api

import com.example.covidtracker.model.Superhero
import retrofit2.http.GET

interface ApiClient {
    @GET("all.json")
    suspend fun getAllSuperheros():List<Superhero>
}