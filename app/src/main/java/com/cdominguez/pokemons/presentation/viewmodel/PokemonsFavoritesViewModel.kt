package com.cdominguez.pokemons.presentation.viewmodel

import androidx.lifecycle.*
import com.cdominguez.domain.FindAllFavoritePokemons
import com.cdominguez.domain.Pokemon
import com.cdominguez.domain.PokemonDetail
import kotlinx.coroutines.launch

class PokemonsFavoritesViewModel(
    findAllFavoritePokemons: FindAllFavoritePokemons
) : ViewModel() {

    val favoritePokemons : LiveData<List<Pokemon>> =
        findAllFavoritePokemons
            .invoke()
            .asLiveData()
}