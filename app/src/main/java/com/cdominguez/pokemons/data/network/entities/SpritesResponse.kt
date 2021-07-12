package com.cdominguez.pokemons.data.network.entities

import com.google.gson.annotations.SerializedName

data class SpritesResponse(
    @SerializedName("other") val other : OtherResponse
)
