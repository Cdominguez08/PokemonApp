package com.cdominguez.pokemons.presentation.viewmodel

import com.cdominguez.domain.*
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class ViewModelModule {

    @Provides
    fun mainViewModelProvider(
      findAllPokemons: FindAllPokemons
    ) = MainViewModel(findAllPokemons)

    @Provides
    fun pokemonDetailViewModelProvider(
        findPokemonDetail: FindPokemonDetail,
        addPokemonToFavorite: AddPokemonToFavorite,
        removePokemonToFavorite: RemovePokemonToFavorite,
        findPokemonById: FindPokemonById
    ) = PokemonDetailViewModel(
        findPokemonDetail,
        addPokemonToFavorite,
        removePokemonToFavorite,
        findPokemonById
    )

    @Provides
    fun pokemonFavoritesViewModelComponent(
        findAllFavoritePokemons: FindAllFavoritePokemons
    ) = PokemonsFavoritesViewModel(findAllFavoritePokemons)
}

@Subcomponent(modules = [(ViewModelModule::class)])
interface MainComponent{
    val mainViewModel : MainViewModel
}

@Subcomponent(modules = [(ViewModelModule::class)])
interface PokemonDetailComponent{
    val pokemonDetailViewModel : PokemonDetailViewModel
}

@Subcomponent(modules = [(ViewModelModule::class)])
interface PokemonFavoriteComponent{
    val pokemonsFavoritesViewModel : PokemonsFavoritesViewModel
}