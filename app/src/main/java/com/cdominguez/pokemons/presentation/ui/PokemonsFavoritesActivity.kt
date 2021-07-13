package com.cdominguez.pokemons.presentation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.cdominguez.domain.FindAllFavoritePokemons
import com.cdominguez.domain.Pokemon
import com.cdominguez.pokemons.MyApp
import com.cdominguez.pokemons.presentation.adapters.PokemonRecyclerViewAdapter
import com.cdominguez.pokemons.data.local.AppDatabase
import com.cdominguez.pokemons.data.local.LocalDataSource
import com.cdominguez.pokemons.data.local.PokemonRoomDataSource
import com.cdominguez.pokemons.data.PokemonRepository
import com.cdominguez.pokemons.data.network.PokemonRequest
import com.cdominguez.pokemons.data.network.PokemonRetrofitDataSource
import com.cdominguez.pokemons.data.network.RemoteDataSource
import com.cdominguez.pokemons.databinding.ActivityPokemonsFavoritesBinding
import com.cdominguez.pokemons.presentation.viewmodel.PokemonsFavoritesViewModel
import com.cdominguez.pokemons.presentation.viewmodel.PokemonFavoriteComponent
import com.cdominguez.pokemons.presentation.viewmodel.ViewModelModule

class PokemonsFavoritesActivity : AppCompatActivity(), PokemonRecyclerViewAdapter.OnPokemonItemListener {

    private lateinit var binding : ActivityPokemonsFavoritesBinding
    private lateinit var adapter : PokemonRecyclerViewAdapter

    private lateinit var pokemonFavoriteComponent : PokemonFavoriteComponent

    private val viewModel : PokemonsFavoritesViewModel by lazy {
        pokemonFavoriteComponent.pokemonsFavoritesViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonsFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Pokemons favoritos"

        pokemonFavoriteComponent = (applicationContext as MyApp).appComponent.injectPokemonsFavoriteComponent(ViewModelModule())

        adapter = PokemonRecyclerViewAdapter(this)
        binding.rvPokemonFavList.adapter = adapter
        binding.rvPokemonFavList.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        viewModel.favoritePokemons
            .observe(this,{
                adapter.clearList()
                adapter.updatePokemonList(it)
            })
    }

    override fun onItemClick(pokemon: Pokemon) {
        val intent = Intent(this, PokemonDetailActivity::class.java)
        intent.putExtra(PokemonDetailActivity.POKEMON_DETAIL_URL,pokemon.detailUrl)
        startActivity(intent)
    }
}