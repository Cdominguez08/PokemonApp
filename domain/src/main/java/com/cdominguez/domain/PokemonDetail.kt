package com.cdominguez.domain

data class PokemonDetail(
    val id : Long,
    val name : String,
    val imageUrl : String?,
    val height : Int,
    val weight : Int
)
