package com.cdominguez.pokemons.usecases

import com.cdominguez.domain.*
import com.cdominguez.pokemons.data.PokemonRepository
import dagger.Module
import dagger.Provides

@Module
class UserCasesModule {

    @Provides
    fun findAllPokemonsProvider(pokemonRepository: PokemonRepository) =
        FindAllPokemons(pokemonRepository)

    @Provides
    fun findPokemonDetailProvider(pokemonRepository: PokemonRepository) =
        FindPokemonDetail(pokemonRepository)

    @Provides
    fun addPokemonToFavoriteProvider(pokemonRepository: PokemonRepository) =
        AddPokemonToFavorite(pokemonRepository)

    @Provides
    fun removePokemonToFavoriteProvider(pokemonRepository: PokemonRepository) =
        RemovePokemonToFavorite(pokemonRepository)

    @Provides
    fun findAllFavoritePokemonsProvider(pokemonRepository: PokemonRepository) =
        FindAllFavoritePokemons(pokemonRepository)

    @Provides
    fun findPokemonByIdProvider(pokemonRepository: PokemonRepository) =
        FindPokemonById(pokemonRepository)
}