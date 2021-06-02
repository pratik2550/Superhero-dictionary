package com.example.covidtracker.repository

import com.example.covidtracker.api.ReferenceSuperhero
import com.example.covidtracker.model.Superhero
import com.example.covidtracker.model.superheroSearch
import retrofit2.Response

class HeroReferenceRepository {
    suspend fun searchSuperhero(id: String): Response<superheroSearch> {
        return ReferenceSuperhero.api.searchSuperhero(id)
    }

    suspend fun getById(hero: String): Response<Superhero> {
        return ReferenceSuperhero.api.getById(hero)
    }
}