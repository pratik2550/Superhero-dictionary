package com.example.covidtracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.covidtracker.repository.HeroReferenceRepository
import com.example.covidtracker.repository.SuperheroRepository

@Suppress("UNCHECKED_CAST")
class ReferenceViewModelFactory (private val repository: HeroReferenceRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SuperHeroReferenceViewModel(repository) as T
    }

}