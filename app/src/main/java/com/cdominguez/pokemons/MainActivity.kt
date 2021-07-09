package com.cdominguez.pokemons

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.cdominguez.domain.Pokemon
import com.cdominguez.pokemons.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pokemonList = mutableListOf<Pokemon>()

        val pokemon1 = Pokemon(name = "bulbasaur", imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png")
        val pokemon2 = Pokemon(name = "ivysaur", imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/2.png")
        val pokemon3 = Pokemon(name = "venusaur", imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/3.png")

        pokemonList.add(pokemon1)
        pokemonList.add(pokemon2)
        pokemonList.add(pokemon3)

        val adapter = PokemonRecyclerViewAdapter()

        binding.rvPokemonList.adapter = adapter
        binding.rvPokemonList.layoutManager = GridLayoutManager(this,2)

        adapter.updatePokemonList(pokemonList)
    }
}