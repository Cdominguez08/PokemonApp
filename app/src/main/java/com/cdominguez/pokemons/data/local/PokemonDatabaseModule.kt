package com.cdominguez.pokemons.data.local

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PokemonDatabaseModule {

    //Base de datos
    @Provides
    @Singleton
    fun databaseProvider(
        app : Application
    ) : AppDatabase = AppDatabase.getDatabase(app)

    @Provides
    fun localDataSourceProvider(
        database: AppDatabase
    ) : LocalDataSource = PokemonRoomDataSource(database)
}