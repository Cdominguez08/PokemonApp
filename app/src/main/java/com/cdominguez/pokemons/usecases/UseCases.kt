package com.cdominguez.domain

import com.cdominguez.pokemons.data.PokemonRepository

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
    suspend fun invoke(pokemonDetail: PokemonDetail) = pokemonRepositoy.addPokemonToFavorite(pokemonDetail)
}

class RemovePokemonToFavorite(
    private val pokemonRepositoy: PokemonRepository
){
    suspend fun invoke(pokemonDetail: PokemonDetail) = pokemonRepositoy.removePokemonToFavorite(pokemonDetail)
}

class FindAllFavoritePokemons(
    val pokemonRepositoy: PokemonRepository
){
    fun invoke() = pokemonRepositoy.findAllFavoritePokemon()
}

class FindPokemonById(
    val pokemonRepositoy: PokemonRepository
){
    suspend fun invoke(pokemonId: Long) = pokemonRepositoy.findPokemonById(pokemonId)
}