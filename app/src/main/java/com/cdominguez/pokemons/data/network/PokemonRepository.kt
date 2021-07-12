package com.cdominguez.pokemons.data.network

import com.cdominguez.domain.Pokemon
import com.cdominguez.domain.PokemonDetail

class PokemonRepository(
    private val remoteDataSource: RemoteDataSource
) {

    suspend fun findAllPokemons(offset : Int) : List<Pokemon>{
        return remoteDataSource.findAllPokemons(offset)
    }

    suspend fun findPokemonDetail(url: String?) : PokemonDetail{
        return remoteDataSource.findPokemonDetail(url)
    }

    fun addPokemonToFavorite(pokemonDetail: PokemonDetail) {

    }
}