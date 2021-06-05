package com.example.covidtracker

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidtracker.model.Superhero
import com.example.covidtracker.repository.HeroReferenceRepository
import com.example.covidtracker.ui.adapter.SearchAdapter
import com.example.covidtracker.viewmodel.ReferenceViewModelFactory
import com.example.covidtracker.viewmodel.SuperHeroReferenceViewModel
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment() {

    private lateinit var viewModel: SuperHeroReferenceViewModel
    private val superHeroesList = arrayListOf<Superhero>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var searchName = "iron"

        val view = inflater.inflate(R.layout.fragment_search, container, false)
//        rvSearch.hasFixedSize();

        searchHeroes(searchName)
//        rvSearch.adapter = myAdapter

        return view;
    }

    private fun searchHeroes(searchName: String) {
        val repository = HeroReferenceRepository()
        val viewModelFactory = ReferenceViewModelFactory(repository);
        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(SuperHeroReferenceViewModel::class.java)
        viewModel.searchSuperhero(searchName)
        viewModel.mySuperHero.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                superHeroesList.clear()
                if (response.body()?.response.equals("success")) {
                    response.body()?.let { v ->
                        v.results?.let { superHeroesList.addAll(it) }
                    }
                    rvSearch.apply {
                        layoutManager = LinearLayoutManager(context)
                        adapter = SearchAdapter(superHeroesList, context);
                    }
                } else {
                    Log.i("ERROR", "No hero found")
                }
            } else {
                Log.i("ERROR", "Problem")
            }
        })

    }
}