package com.example.covidtracker.repository

import com.example.covidtracker.api.SuperheroInstant
import com.example.covidtracker.model.Superhero

class SuperheroRepository {
    suspend fun getAllSuperhero(): List<Superhero> {
        return SuperheroInstant.api.getAllSuperheros()
    }
}