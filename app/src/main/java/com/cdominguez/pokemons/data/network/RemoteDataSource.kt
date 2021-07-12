package com.cdominguez.pokemons.data.network

import com.cdominguez.domain.Pokemon
import com.cdominguez.domain.PokemonDetail

interface RemoteDataSource {
    suspend fun findAllPokemons(offset: Int) : List<Pokemon>

    suspend fun findPokemonDetail(url : String?) : PokemonDetail
}