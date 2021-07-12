package com.cdominguez.pokemons.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cdominguez.domain.*
import kotlinx.coroutines.launch

class PokemonDetailViewModel(
    private val findPokemonDetail: FindPokemonDetail,
    private val addPokemonToFavorite: AddPokemonToFavorite,
    private val removePokemonToFavorite: RemovePokemonToFavorite,
    private val findPokemonById: FindPokemonById
) : ViewModel() {

    private val _pokemonDetail = MutableLiveData<PokemonDetail>()
    val pokemonDetail : LiveData<PokemonDetail> get() = _pokemonDetail

    private val _showProgressBar = MutableLiveData<Boolean>()
    val showProgressBar : LiveData<Boolean> get() = _showProgressBar

    private val _isPokemonFavorite = MutableLiveData<Boolean>()
    val isPokemonFavorite : LiveData<Boolean> get() = _isPokemonFavorite

    fun findPokemonDetail(url : String?){
        viewModelScope.launch {
            try{

                _showProgressBar.value = true
                val pokemonDetailResponse =  findPokemonDetail.invoke(url)
                _pokemonDetail.value = pokemonDetailResponse
            }catch (e: Throwable){

                _showProgressBar.value = false
            }finally {

                _showProgressBar.value = false
            }
        }
    }

    fun addPokemonToFavorite(pokemonDetail: PokemonDetail) {
        viewModelScope.launch {
            addPokemonToFavorite.invoke(pokemonDetail)
            _isPokemonFavorite.value = true
        }
    }

    fun removePokemonToFavorite(pokemonDetail: PokemonDetail){
        viewModelScope.launch {
            removePokemonToFavorite.invoke(pokemonDetail)
            _isPokemonFavorite.value = false
        }
    }

    fun findPokemonById(pokemonId: Long){

        viewModelScope.launch {
            val pokemon : PokemonDetail? = try{
                findPokemonById.invoke(pokemonId)
            }catch (e: Throwable){
                null
            }

            if(pokemon != null){
                _isPokemonFavorite.value = true
            }else{
                _isPokemonFavorite.value = false
            }
        }
    }
}