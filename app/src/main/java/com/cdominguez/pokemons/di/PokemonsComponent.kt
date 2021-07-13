package com.cdominguez.pokemons.di

import android.app.Application
import com.cdominguez.pokemons.data.PokemonRepositoryModule
import com.cdominguez.pokemons.data.local.PokemonDatabaseModule
import com.cdominguez.pokemons.data.network.PokemonApiModule
import com.cdominguez.pokemons.presentation.viewmodel.MainComponent
import com.cdominguez.pokemons.presentation.viewmodel.PokemonDetailComponent
import com.cdominguez.pokemons.presentation.viewmodel.PokemonFavoriteComponent
import com.cdominguez.pokemons.presentation.viewmodel.ViewModelModule
import com.cdominguez.pokemons.usecases.UserCasesModule
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [PokemonDatabaseModule::class,PokemonApiModule::class,PokemonRepositoryModule::class,UserCasesModule::class,])
interface PokemonsComponent {

    fun injectMainComponent(module : ViewModelModule) : MainComponent
    fun injectPokemonDetailComponent(module : ViewModelModule) : PokemonDetailComponent
    fun injectPokemonsFavoriteComponent(module: ViewModelModule) : PokemonFavoriteComponent


    @Component.Factory
    interface createComponent{
        fun create(@BindsInstance app: Application) : PokemonsComponent
    }
}