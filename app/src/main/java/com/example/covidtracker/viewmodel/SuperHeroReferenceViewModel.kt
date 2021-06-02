package com.example.covidtracker.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covidtracker.model.Superhero
import com.example.covidtracker.model.superheroSearch
import com.example.covidtracker.repository.HeroReferenceRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class SuperHeroReferenceViewModel(private val repository: HeroReferenceRepository): ViewModel() {

    var mySuperHero: MutableLiveData<Response<superheroSearch>> = MutableLiveData()
    var mySuperHeroProfile: MutableLiveData<Response<Superhero>> = MutableLiveData()

    fun searchSuperhero(hero: String) {
        viewModelScope.launch {
            val response = repository.searchSuperhero(hero)
            mySuperHero.value = response
        }
    }

    fun getProfile(id: String) {
        viewModelScope.launch {
            val response = repository.getById(id)
            mySuperHeroProfile.value = response
        }
    }
}