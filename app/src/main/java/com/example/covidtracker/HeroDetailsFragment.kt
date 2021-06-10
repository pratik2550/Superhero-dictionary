package com.example.covidtracker

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.covidtracker.model.Superhero
import com.example.covidtracker.repository.HeroReferenceRepository
import com.example.covidtracker.viewmodel.ReferenceViewModelFactory
import com.example.covidtracker.viewmodel.SuperHeroReferenceViewModel
import kotlinx.android.synthetic.main.fragment_hero_details.*
import retrofit2.Response

class HeroDetailsFragment : Fragment() {

    private lateinit var viewModel: SuperHeroReferenceViewModel
    private val superHeroesList = arrayListOf<Superhero>()

    private val args: HeroDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_hero_details, container, false)

        val heroId = args.heroId
        val repository = HeroReferenceRepository()
        val viewModelFactory = ReferenceViewModelFactory(repository)
        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(SuperHeroReferenceViewModel::class.java)

        if (heroId != null) {
            viewModel.getProfile(heroId)
        }
        viewModel.mySuperHeroProfile.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                getHeroDetails(response)
            }
        })

        return view
    }

    private fun getHeroDetails(response: Response<Superhero>) {
        val profileModel: Superhero? = response.body()
        if (profileModel != null) {
            Glide.with(this)
                .load(profileModel.image?.url.toString())
                .into(ivProfile)

            tvName.text = profileModel.name

            if (profileModel.name.isNullOrEmpty()) {
                tvFullName.visibility = View.GONE
            } else {
                tvFullName.text = "Full name:- " + profileModel.name
            }
            if (profileModel.biography?.placeOfBirth.isNullOrEmpty()) {
                tvPlaceOfBirth.visibility = View.GONE
            } else {
                tvPlaceOfBirth.text = "Place of Birth:- " + profileModel.biography?.placeOfBirth
            }
            if (profileModel.biography?.firstAppearance.isNullOrEmpty()) {
                tvFirstAppearance.visibility = View.GONE
            } else {
                tvFirstAppearance.text =
                    "First Appearance:- " + profileModel.biography?.firstAppearance
            }
            if (profileModel.biography?.publisher.isNullOrEmpty()) {
                tvPublisher.visibility = View.GONE
            } else {
                tvPublisher.text = "Publisher:- " + profileModel.biography?.publisher
            }
            if (profileModel.biography?.alignment.isNullOrEmpty()) {
                tvAlignment.visibility = View.GONE
            } else {
                tvAlignment.text = "Alignment:- " + profileModel.biography?.alignment
            }

            if (profileModel.appearance?.gender.isNullOrEmpty()) {
                tvGender.visibility = View.GONE
            } else {
                tvGender.text = "Gender:- " + profileModel.appearance?.gender
            }
            if (profileModel.appearance?.race.isNullOrEmpty()) {
                tvRace.visibility = View.GONE
            } else {
                tvRace.text = "Race:- " + profileModel.appearance?.race
            }
            if (profileModel.appearance?.height?.get(0).isNullOrEmpty()) {
                tvHeight.visibility = View.GONE
            } else {
                tvHeight.text = "Height:- " + profileModel.appearance?.height?.get(0)
            }
            if (profileModel.appearance?.weight?.get(0).isNullOrEmpty()) {
                tvWeight.visibility = View.GONE
            } else {
                tvWeight.text = "Weight:- " + profileModel.appearance?.weight?.get(0)
            }
            if (profileModel.appearance?.eyeColor.isNullOrEmpty()) {
                tvEyeColour.visibility = View.GONE
            } else {
                tvEyeColour.text = "EyeColour:- " + profileModel.appearance?.eyeColor
            }
            if (profileModel.appearance?.hairColor.isNullOrEmpty()) {
                tvHairColour.visibility = View.GONE
            } else {
                tvHairColour.text = "HairColour:- " + profileModel.appearance?.hairColor
            }

            if (profileModel.powerstats?.intelligence.isNullOrEmpty()) {
                tvIntelligence.visibility = View.GONE
            } else {
                tvIntelligence.text = "Intelligence:- " + profileModel.powerstats?.intelligence
            }
            if (profileModel.powerstats?.strength.isNullOrEmpty()) {
                tvStrength.visibility = View.GONE
            } else {
                tvStrength.text = "Strength:- " + profileModel.powerstats?.strength
            }
            if (profileModel.powerstats?.speed.isNullOrEmpty()) {
                tvSpeed.visibility = View.GONE
            } else {
                tvSpeed.text = "Speed:- " + profileModel.powerstats?.speed
            }
            if (profileModel.powerstats?.durability.isNullOrEmpty()) {
                tvDurability.visibility = View.GONE
            } else {
                tvDurability.text = "Durability:- " + profileModel.powerstats?.durability
            }
            if (profileModel.powerstats?.power.isNullOrEmpty()) {
                tvPower.visibility = View.GONE
            } else {
                tvPower.text = "Power:- " + profileModel.powerstats?.power
            }
            if (profileModel.powerstats?.combat.isNullOrEmpty()) {
                tvCombat.visibility = View.GONE
            } else {
                tvCombat.text = "Combat:- " + profileModel.powerstats?.combat
            }

            //Connection
            //alterEgos
            if (profileModel.biography?.alterEgos.isNullOrEmpty()) {
                tvAlterEgos.visibility = View.GONE
            } else {
//                val alter = profileModel.biography?.alterEgos.split()
                tvAlterEgos.text = "Alter Egos:- " + profileModel.biography?.alterEgos
            }
            //relatives
            if (profileModel.connections?.relatives.isNullOrEmpty()) {
                tvRelatives.visibility = View.GONE
            } else {
                val relative = profileModel.connections?.relatives?.split(", ")
                var rel: String = ""
                relative?.forEach { rel = rel + it + "\n\t\t\t\t" }
                tvRelatives.text = "Relatives:- " + rel
            }
            //groupAffiliation
            if (profileModel.connections?.groupAffiliation.isNullOrEmpty()) {
                tvAffiliation.visibility = View.GONE
            } else {
                val affiliation = profileModel.connections?.groupAffiliation?.split(", ")
                var aff: String = ""
                affiliation?.forEach { aff = aff + it + "\n\t\t\t\t" }
                tvAffiliation.text = "Affiliation:- " + aff
            }
            //base
            if (profileModel.work?.base.isNullOrEmpty()) {
                tvBase.visibility = View.GONE
            } else {
                val base = profileModel.work?.base?.split(";")
                var bas = ""
                base?.forEach{bas = bas + it + "\n\t\t\t\t\t\t\t"}
                tvBase.text = "Base:- " + bas
            }
            //occupation
            if (profileModel.work?.occupation.isNullOrEmpty()) {
                tvOccupation.visibility = View.GONE
            } else {
                val occupation = profileModel.work?.occupation?.split(",")
                var occ = ""
                occupation?.forEach{occ = occ + it + "\n\t\t\t\t\t\t"}
                tvOccupation.text = "Occupation:- " + occ
            }
        }
    }
}

