package com.example.covidtracker.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covidtracker.model.Superhero
import com.example.covidtracker.repository.SuperheroRepository
import kotlinx.coroutines.launch

class SuperHeroViewModel(private val repository: SuperheroRepository) : ViewModel() {

    val myResponse: MutableLiveData<List<Superhero>> = MutableLiveData()

    fun getAllSuperhero() {
        viewModelScope.launch {
            val response: List<Superhero> = repository.getAllSuperhero()
            myResponse.value = response
        }
    }
}