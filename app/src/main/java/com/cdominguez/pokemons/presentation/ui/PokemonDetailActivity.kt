package com.cdominguez.pokemons.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.cdominguez.domain.*
import com.cdominguez.pokemons.R
import com.cdominguez.pokemons.data.local.AppDatabase
import com.cdominguez.pokemons.data.local.LocalDataSource
import com.cdominguez.pokemons.data.local.PokemonRoomDataSource
import com.cdominguez.pokemons.data.PokemonRepository
import com.cdominguez.pokemons.data.network.PokemonRequest
import com.cdominguez.pokemons.data.network.PokemonRequest.Companion.baseUrl
import com.cdominguez.pokemons.data.network.PokemonRetrofitDataSource
import com.cdominguez.pokemons.data.network.RemoteDataSource
import com.cdominguez.pokemons.databinding.ActivityPokemonDetailBinding
import com.cdominguez.pokemons.presentation.viewmodel.PokemonDetailViewModel
import com.cdominguez.pokemons.utils.getViewModel
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

    private val localDataSource : LocalDataSource by lazy {
        PokemonRoomDataSource(AppDatabase.getDatabase(applicationContext))
    }

    private val pokemonRepository : PokemonRepository by lazy {
        PokemonRepository(remoteDataSource,localDataSource)
    }

    private val findPokemonDetail : FindPokemonDetail by lazy {
        FindPokemonDetail(pokemonRepository)
    }

    private val addPokemonToFavorite : AddPokemonToFavorite by lazy{
        AddPokemonToFavorite(pokemonRepository)
    }

    private val removePokemonToFavorite: RemovePokemonToFavorite by lazy{
        RemovePokemonToFavorite(pokemonRepository)
    }

    private val findPokemonById : FindPokemonById by lazy{
        FindPokemonById(pokemonRepository)
    }

    private val viewModel : PokemonDetailViewModel by lazy {
        getViewModel {
            PokemonDetailViewModel(
                findPokemonDetail,
                addPokemonToFavorite,
                removePokemonToFavorite,
                findPokemonById
            )
        }
    }

    private var isFavoritePokemon = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Detalle del Pokemon"

        val pokemonDetailUrl = intent.getStringExtra(POKEMON_DETAIL_URL)

        viewModel.findPokemonDetail(pokemonDetailUrl?.replace(baseUrl,""))

        viewModel.pokemonDetail.observe(this, {
            it?.let {
                pokemonDetail = it
                viewModel.findPokemonById(pokemonDetail.id)

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

            if(isFavoritePokemon){
                viewModel.removePokemonToFavorite(pokemonDetail)
            }else {

                viewModel.addPokemonToFavorite(pokemonDetail)
            }
        }

        viewModel.isPokemonFavorite.observe(this,{

            isFavoritePokemon = it

            if(it){
                binding.fbFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
            }else{
                binding.fbFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }
        })
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