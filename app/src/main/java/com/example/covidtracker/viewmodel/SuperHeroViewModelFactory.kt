package com.example.covidtracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.covidtracker.repository.SuperheroRepository

class SuperHeroViewModelFactory (private val repository: SuperheroRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SuperHeroViewModel(repository) as T
    }

}