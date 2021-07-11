package com.cdominguez.domain

import com.cdominguez.pokemons.data.network.PokemonRepository

class FindAllPokemons(
    val pokemonRepositoy : PokemonRepository
){
    suspend fun invoke(offset: Int) = pokemonRepositoy.findAllPokemons(offset)
}