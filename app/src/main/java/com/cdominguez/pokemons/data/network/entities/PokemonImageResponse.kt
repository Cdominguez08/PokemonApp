package com.cdominguez.pokemons.data.network.entities

import com.google.gson.annotations.SerializedName

data class PokemonImageResponse(
    @SerializedName("front_default") val imageUrl : String?
)
