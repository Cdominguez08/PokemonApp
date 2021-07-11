package com.cdominguez.pokemons.data.network.entities

import com.google.gson.annotations.SerializedName

data class PokemonMainResponse(
    @SerializedName("count") val count : Int,
    @SerializedName("next") val next : String,
    @SerializedName("previous") val previous : String?,
    @SerializedName("results") val pokemons : List<PokemonResponse>
    )
