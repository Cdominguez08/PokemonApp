package com.cdominguez.pokemons.data.network.entities

import com.google.gson.annotations.SerializedName

data class OtherResponse(
    @SerializedName("dream_world") val dreamWorld : PokemonImageResponse,
    @SerializedName("official-artwork") val officialArtWork : PokemonImageResponse
)
