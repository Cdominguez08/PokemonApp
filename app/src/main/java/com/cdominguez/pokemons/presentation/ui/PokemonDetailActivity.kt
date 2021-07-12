package com.cdominguez.pokemons.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.cdominguez.domain.FindPokemonDetail
import com.cdominguez.domain.PokemonDetail
import com.cdominguez.pokemons.R
import com.cdominguez.pokemons.data.network.PokemonRepository
import com.cdominguez.pokemons.data.network.PokemonRequest
import com.cdominguez.pokemons.data.network.PokemonRequest.Companion.baseUrl
import com.cdominguez.pokemons.data.network.PokemonRetrofitDataSource
import com.cdominguez.pokemons.data.network.RemoteDataSource
import com.cdominguez.pokemons.databinding.ActivityPokemonDetailBinding
import com.cdominguez.pokemons.presentation.viewmodel.PokemonDetailViewModel
import com.cdominguez.pokemons.utils.getViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso

class PokemonDetailActivity : AppCompatActivity() {

    lateinit var binding : ActivityPokemonDetailBinding
    private lateinit var pokemonDetail: PokemonDetail

    private val pokemonRequest : PokemonRequest by lazy {
        PokemonRequest()
    }

    private val remoteDataSource : RemoteDataSource by lazy {
        PokemonRetrofitDataSource(pokemonRequest)
    }

    private val pokemonRepository : PokemonRepository by lazy {
        PokemonRepository(remoteDataSource)
    }

    private val findPokemonDetail : FindPokemonDetail by lazy {
        FindPokemonDetail(pokemonRepository)
    }

    private val viewModel : PokemonDetailViewModel by lazy {
        getViewModel {
            PokemonDetailViewModel(findPokemonDetail)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pokemonDetailUrl = intent.getStringExtra(POKEMON_DETAIL_URL)

        viewModel.findPokemonDetail(pokemonDetailUrl?.replace(baseUrl,""))

        viewModel.pokemonDetail.observe(this, {
            it?.let {
                pokemonDetail = it
                loadImage(it.imageUrl)
                binding.tvNameDetail.text = it.name
            }
        })

        viewModel.showProgressBar.observe(this,{
            if(it){
                binding.pbLoading.visibility = View.VISIBLE
            }else{
                binding.pbLoading.visibility = View.GONE
            }
        })

        binding.fbFavorite.setOnClickListener{
            viewModel.addPokemonToFavorite(pokemonDetail)
        }
    }

    fun loadImage(imageUrl : String?){
        Picasso.get().load(imageUrl)
            .placeholder(R.drawable.ic_baseline_image_24)
            .error(R.drawable.ic_baseline_broken_image_24)
            .into(binding.ivImage)
    }

    companion object{
        val POKEMON_DETAIL_URL = "POKEMON_DETAIL"
    }
}