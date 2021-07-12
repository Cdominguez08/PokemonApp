package com.cdominguez.pokemons.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cdominguez.domain.AddPokemonToFavorite
import com.cdominguez.domain.FindPokemonDetail
import com.cdominguez.domain.PokemonDetail
import kotlinx.coroutines.launch

class PokemonDetailViewModel(
    private val findPokemonDetail: FindPokemonDetail,
    private val addPokemonToFavorite: AddPokemonToFavorite
) : ViewModel() {

    private val _pokemonDetail = MutableLiveData<PokemonDetail>()
    val pokemonDetail : LiveData<PokemonDetail> get() = _pokemonDetail

    private val _showProgressBar = MutableLiveData<Boolean>()
    val showProgressBar : LiveData<Boolean> get() = _showProgressBar

    fun findPokemonDetail(url : String?){
        viewModelScope.launch {
            try{

                _showProgressBar.value = true
                val pokemonDetailResponse =  findPokemonDetail.invoke(url)
                _pokemonDetail.value = pokemonDetailResponse
            }catch (e: Throwable){

                Log.i("PokemonDetail","Error en obtener pokemon detail ${e.localizedMessage}")
                _showProgressBar.value = false
            }finally {

                _showProgressBar.value = false
            }
        }
    }

    fun addPokemonToFavorite(pokemonDetail: PokemonDetail) {
        addPokemonToFavorite.invoke(pokemonDetail)
    }
}