package com.example.covidtracker

import android.accessibilityservice.AccessibilityService
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.SearchView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidtracker.model.Superhero
import com.example.covidtracker.repository.HeroReferenceRepository
import com.example.covidtracker.ui.adapter.OnSearchedItemClickListener
import com.example.covidtracker.ui.adapter.SearchAdapter
import com.example.covidtracker.viewmodel.ReferenceViewModelFactory
import com.example.covidtracker.viewmodel.SuperHeroReferenceViewModel
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.view.*

class SearchFragment : Fragment(), OnSearchedItemClickListener {

    private lateinit var viewModel: SuperHeroReferenceViewModel
    private val superHeroesList = arrayListOf<Superhero>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_search, container, false)
        view.searchView.onActionViewExpanded()
        view.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { searchHeroes(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { searchHeroes(it) }
                return true
            }

        })

        return view;
    }

    private fun searchHeroes(searchName: String) {
        val repository = HeroReferenceRepository()
        val viewModelFactory = ReferenceViewModelFactory(repository)
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
                        adapter = SearchAdapter(superHeroesList, this@SearchFragment, context);
                    }
                } else {
                    Log.i("ERROR", "No hero found")
                }
            } else {
                Log.i("ERROR", "Problem")
            }
        })

    }

    override fun onItemClick(position: Int) {
        val action =
            SearchFragmentDirections.actionSearchFragmentToHeroDetailsFragment(superHeroesList[position].id.toString())
        Navigation.findNavController(requireView()).navigate(action)
//        Toast.makeText(context, "clicked", Toast.LENGTH_LONG).show()
    }
}