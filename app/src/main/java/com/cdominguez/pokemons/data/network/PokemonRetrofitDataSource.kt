package com.cdominguez.pokemons.data.network

import com.cdominguez.domain.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonRetrofitDataSource(
    private val pokemonRequest: PokemonRequest
) : RemoteDataSource{

    override suspend fun findAllPokemons(offset: Int): List<Pokemon> = withContext(Dispatchers.IO) {
        val pokemonResponse = try{
            pokemonRequest.findAllPokemons(offset)
        }catch (e : Throwable){
            throw Throwable("Error al obtener listado de pokemones")
        }

        pokemonResponse.pokemons.map { it.toPokemon() }
    }
}