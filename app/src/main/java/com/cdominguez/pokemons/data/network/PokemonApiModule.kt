package com.cdominguez.pokemons.data.network

import dagger.Module
import dagger.Provides

@Module
class PokemonApiModule {

    //Pokemon request
    @Provides
    fun pokemonRequestProvider() = PokemonRequest()

    //RemoteDataSource
    @Provides
    fun remotePokemonDataSourceProvider(
        pokemonRequest: PokemonRequest
    ) : RemoteDataSource = PokemonRetrofitDataSource(pokemonRequest)
}
