package com.cdominguez.pokemons.data

import com.cdominguez.domain.Pokemon
import com.cdominguez.domain.PokemonDetail
import com.cdominguez.pokemons.data.local.LocalDataSource
import com.cdominguez.pokemons.data.network.RemoteDataSource
import kotlinx.coroutines.flow.Flow

class PokemonRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {

    suspend fun findAllPokemons(offset : Int) : List<Pokemon>{
        return remoteDataSource.findAllPokemons(offset)
    }

    suspend fun findPokemonDetail(url: String?) : PokemonDetail{
        return remoteDataSource.findPokemonDetail(url)
    }

    suspend fun addPokemonToFavorite(pokemonDetail: PokemonDetail) {
        localDataSource.addPokemonToFavorite(pokemonDetail)
    }

    suspend fun removePokemonToFavorite(pokemonDetail: PokemonDetail){
        localDataSource.removePokemonToFavorite(pokemonDetail)
    }

    fun findAllFavoritePokemon() : Flow<List<Pokemon>>{
        return localDataSource.findAllFavoritePokemons()
    }

    suspend fun findPokemonById(pokemonId: Long): PokemonDetail {
        return localDataSource.findPokemonById(pokemonId)
    }
}