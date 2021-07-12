package com.cdominguez.domain

import com.cdominguez.pokemons.data.network.PokemonRepository

class FindAllPokemons(
    val pokemonRepositoy : PokemonRepository
){
    suspend fun invoke(offset: Int) = pokemonRepositoy.findAllPokemons(offset)
}

class FindPokemonDetail(
    val pokemonRepositoy: PokemonRepository
){
    suspend fun invoke(url: String?) = pokemonRepositoy.findPokemonDetail(url)
}

class AddPokemonToFavorite(
    private val pokemonRepositoy: PokemonRepository
){
    fun invoke(pokemonDetail: PokemonDetail) = pokemonRepositoy.addPokemonToFavorite(pokemonDetail)
}