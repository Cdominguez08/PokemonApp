package com.cdominguez.pokemons.data.network

import com.cdominguez.pokemons.data.network.entities.PokemonMainResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonService {

    @GET("/api/v2/pokemon/?limit=20")
    suspend fun findAllPokemons(@Query("offset") offset : Int) : PokemonMainResponse
}