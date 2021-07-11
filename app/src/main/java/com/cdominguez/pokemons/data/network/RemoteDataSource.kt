package com.cdominguez.pokemons.data.network

import com.cdominguez.domain.Pokemon

interface RemoteDataSource {
    suspend fun findAllPokemons(offset: Int) : List<Pokemon>
}