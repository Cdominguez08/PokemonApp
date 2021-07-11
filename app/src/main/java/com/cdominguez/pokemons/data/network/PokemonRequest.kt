package com.cdominguez.pokemons.data.network

import com.cdominguez.domain.Pokemon
import com.cdominguez.pokemons.data.network.entities.PokemonMainResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonRequest {

    private fun buildRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    suspend fun findAllPokemons(offset: Int): PokemonMainResponse {
        return buildRetrofit().create(PokemonService::class.java).findAllPokemons(offset)
    }

    companion object{
        val baseUrl = "https://pokeapi.co/"
    }
}