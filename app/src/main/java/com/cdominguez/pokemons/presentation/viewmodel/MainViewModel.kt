package com.cdominguez.pokemons.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cdominguez.domain.FindAllPokemons
import com.cdominguez.domain.Pokemon
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainViewModel(
    private val findAllPokemons: FindAllPokemons
) : ViewModel() {

    var offset = 0
    private var isLastPage = false
    private var isLoading = false

    private var _pokemonsMutableLiveData = MutableLiveData<List<Pokemon>>()
    val pokemons : LiveData<List<Pokemon>> get() = _pokemonsMutableLiveData

    fun findAllPokemons(){
        viewModelScope.launch {
            try{
                val results = findAllPokemons.invoke(offset)
                _pokemonsMutableLiveData.value = results
            }catch (e: Throwable){

            }
        }
    }

    fun onLoadMoreItems(visibleItemCount: Int, firstVisibleItemPosition: Int, totalItemCount: Int) {
        if (isLoading || isLastPage || !isInFooter(visibleItemCount, firstVisibleItemPosition, totalItemCount)) {
            return
        }

        offset += PAGE_SIZE
        findAllPokemons()
    }

    private fun isInFooter(
        visibleItemCount: Int,
        firstVisibleItemPosition: Int,
        totalItemCount: Int
    ): Boolean {
        return visibleItemCount + firstVisibleItemPosition >= totalItemCount
                && firstVisibleItemPosition >= 0
                && totalItemCount >= PAGE_SIZE
    }

    companion object{

        val PAGE_SIZE = 20
    }
}