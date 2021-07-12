package com.cdominguez.pokemons.data.local

import androidx.room.*
import com.cdominguez.pokemons.data.local.entities.PokemonEntity
import io.reactivex.Flowable
import io.reactivex.Maybe

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemon : PokemonEntity)

    @Delete
    suspend fun deletePokemon(pokemon : PokemonEntity)

    @Query("SELECT * FROM PokemonEntity")
    fun findAllPokemons() : Flowable<List<PokemonEntity>>

    @Query("SELECT * FROM PokemonEntity WHERE id = :pokemonId")
    fun findPokemonById(pokemonId : Long) : Maybe<PokemonEntity>
}