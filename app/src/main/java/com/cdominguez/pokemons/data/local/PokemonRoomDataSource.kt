package com.cdominguez.pokemons.data.local

import com.cdominguez.domain.Pokemon
import com.cdominguez.domain.PokemonDetail
import com.cdominguez.pokemons.data.local.entities.PokemonEntity
import com.cdominguez.pokemons.data.network.toPokemonDetail
import com.cdominguez.pokemons.data.network.toPokemonDomainList
import com.cdominguez.pokemons.data.network.toPokemonEntity
import io.reactivex.Flowable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonRoomDataSource(
    private val database: AppDatabase
) : LocalDataSource {

    private val pokemonDao : PokemonDao by lazy {
        database.pokemonDao()
    }

    override suspend fun findAllFavoritePokemons(): Flowable<List<PokemonDetail>> {
        return pokemonDao
            .findAllPokemons()
            .map(List<PokemonEntity>::toPokemonDomainList)
    }

    override suspend fun addPokemonToFavorite(pokemon: PokemonDetail) = withContext(Dispatchers.IO) {
        pokemonDao.insertPokemon(pokemon.toPokemonEntity())
    }

    override suspend fun removePokemonToFavorite(pokemon: PokemonDetail) {
        pokemonDao.deletePokemon(pokemon.toPokemonEntity())
    }

    override suspend fun findPokemonById(pokemonId: Long): PokemonDetail = withContext(Dispatchers.IO){
        val pokemonEntity = try{
            pokemonDao.findPokemonById(pokemonId).blockingGet()
        }catch (e : Throwable){
            throw e
        }

        pokemonEntity.toPokemonDetail()
    }
}