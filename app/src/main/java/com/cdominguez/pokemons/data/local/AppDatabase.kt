package com.cdominguez.pokemons.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cdominguez.pokemons.data.local.entities.PokemonEntity

@Database(
    entities = arrayOf(PokemonEntity::class),
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun pokemonDao() : PokemonDao

    companion object {

        private const val DATABASE_NAME = "pokemon.db"

        @Synchronized
        fun getDatabase(context: Context): AppDatabase = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}