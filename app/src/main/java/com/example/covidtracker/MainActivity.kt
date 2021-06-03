package com.example.covidtracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.covidtracker.repository.SuperheroRepository
import com.example.covidtracker.viewmodel.SuperHeroViewModel
import com.example.covidtracker.viewmodel.SuperHeroViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: SuperHeroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("All superhero", "Pratik")
        val repository = SuperheroRepository()
        val viewModelFactory = SuperHeroViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SuperHeroViewModel::class.java)
        viewModel.getAllSuperhero()
        viewModel.myResponse.observe(this, Observer { response->
            Log.i("All superhero", response.toString())
        })
    }
}