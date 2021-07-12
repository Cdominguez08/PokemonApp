package com.cdominguez.pokemons.data.network.entities

import com.google.gson.annotations.SerializedName

data class PokemonDetailResponse(
    @SerializedName("sprites") val spritesResponse: SpritesResponse,
    @SerializedName("height") val height: Int,
    @SerializedName("name") val name: String,
    @SerializedName("weight") val weight: Int,
    @SerializedName("id") val id: Long,
    var detailUrl : String?
)
