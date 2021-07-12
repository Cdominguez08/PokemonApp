package com.cdominguez.pokemons.data.local

import com.cdominguez.domain.Pokemon
import com.cdominguez.domain.PokemonDetail
import com.cdominguez.pokemons.data.local.entities.PokemonEntity
import io.reactivex.Flowable

interface LocalDataSource {

    suspend fun findAllFavoritePokemons() : Flowable<List<PokemonDetail>>

    suspend fun addPokemonToFavorite(pokemon : PokemonDetail)

    suspend fun removePokemonToFavorite(pokemon: PokemonDetail)

    suspend fun findPokemonById(pokemonId : Long) : PokemonDetail
}