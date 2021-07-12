package com.cdominguez.pokemons.data.local

import com.cdominguez.domain.Pokemon
import com.cdominguez.domain.PokemonDetail
import com.cdominguez.pokemons.data.local.entities.PokemonEntity
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    fun findAllFavoritePokemons() : Flow<List<Pokemon>>

    suspend fun addPokemonToFavorite(pokemon : PokemonDetail)

    suspend fun removePokemonToFavorite(pokemon: PokemonDetail)

    suspend fun findPokemonById(pokemonId : Long) : PokemonDetail
}