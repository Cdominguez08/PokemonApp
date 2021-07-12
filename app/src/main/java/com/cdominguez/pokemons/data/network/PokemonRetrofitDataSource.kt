package com.cdominguez.pokemons.data.network

import android.util.Log
import com.cdominguez.domain.Pokemon
import com.cdominguez.domain.PokemonDetail
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonRetrofitDataSource(
    private val pokemonRequest: PokemonRequest
) : RemoteDataSource{

    override suspend fun findAllPokemons(offset: Int): List<Pokemon> = withContext(Dispatchers.IO) {
        val pokemonResponse = try{
            pokemonRequest.findAllPokemons(offset)
        }catch (e : Throwable){
            throw e
        }

        pokemonResponse.pokemons.map { it.toPokemon() }
    }

    override suspend fun findPokemonDetail(url: String?): PokemonDetail = withContext(Dispatchers.IO){

        val pokemonDetail = try{
            pokemonRequest.findPokemonDetail(url)
        }catch (e : Throwable){
            throw e
        }

        pokemonDetail.detailUrl = url
        pokemonDetail.toPokemonDetail()
    }
}