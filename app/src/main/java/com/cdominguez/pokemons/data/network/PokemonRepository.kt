package com.cdominguez.pokemons.data.network

import com.cdominguez.domain.Pokemon

class PokemonRepository(
    private val remoteDataSource: RemoteDataSource
) {

    suspend fun findAllPokemons(offset : Int) : List<Pokemon>{
        return remoteDataSource.findAllPokemons(offset)
    }
}