package com.cdominguez.pokemons.data

import com.cdominguez.pokemons.data.local.LocalDataSource
import com.cdominguez.pokemons.data.network.RemoteDataSource
import dagger.Module
import dagger.Provides

//en esta clase se podrian agregar los "n" repositories que se tengan

@Module
class PokemonRepositoryModule {

    @Provides
    fun pokemonRepositoryProvider(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ) = PokemonRepository(remoteDataSource,localDataSource)
}