package com.cdominguez.pokemons.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cdominguez.domain.FindAllPokemons
import com.cdominguez.domain.Pokemon
import com.cdominguez.pokemons.PokemonRecyclerViewAdapter
import com.cdominguez.pokemons.data.network.PokemonRepository
import com.cdominguez.pokemons.data.network.PokemonRequest
import com.cdominguez.pokemons.data.network.PokemonRetrofitDataSource
import com.cdominguez.pokemons.data.network.RemoteDataSource
import com.cdominguez.pokemons.databinding.ActivityMainBinding
import com.cdominguez.pokemons.presentation.viewmodel.MainViewModel
import com.cdominguez.pokemons.utils.getViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private val pokemonRequest : PokemonRequest by lazy {
        PokemonRequest()
    }

    private val pokemonRemoteDataSource : RemoteDataSource by lazy {
        PokemonRetrofitDataSource(pokemonRequest)
    }

    private val pokemonRepository : PokemonRepository by lazy {
        PokemonRepository(pokemonRemoteDataSource)
    }

    private val findAllPokemons : FindAllPokemons by lazy {
        FindAllPokemons(pokemonRepository)
    }

    private val viewModel : MainViewModel by lazy {
        getViewModel{
            MainViewModel(findAllPokemons)
        }
    }

    private val onScrollListener: RecyclerView.OnScrollListener by lazy {
        object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount: Int = layoutManager.childCount
                val totalItemCount: Int = layoutManager.itemCount
                val firstVisibleItemPosition: Int = layoutManager.findFirstVisibleItemPosition()

                viewModel.onLoadMoreItems(visibleItemCount, firstVisibleItemPosition, totalItemCount)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val pokemonList = mutableListOf<Pokemon>()
//
//        val pokemon1 = Pokemon(name = "bulbasaur", imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png")
//        val pokemon2 = Pokemon(name = "ivysaur", imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/2.png")
//        val pokemon3 = Pokemon(name = "venusaur", imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/3.png")
//
//        pokemonList.add(pokemon1)
//        pokemonList.add(pokemon2)
//        pokemonList.add(pokemon3)

        val adapter = PokemonRecyclerViewAdapter()

        binding.rvPokemonList.adapter = adapter
        binding.rvPokemonList.addOnScrollListener(onScrollListener)
        binding.rvPokemonList.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        //adapter.updatePokemonList(pokemonList)

        viewModel.findAllPokemons()

        viewModel.pokemons.observe(this,{
            it?.let {
                adapter.updatePokemonList(it)
            }
        })
    }
}