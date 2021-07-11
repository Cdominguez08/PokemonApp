package com.cdominguez.pokemons.data.network

import com.cdominguez.domain.Pokemon
import com.cdominguez.pokemons.data.network.entities.PokemonResponse

fun PokemonResponse.toPokemon() = Pokemon(
    name,
    url
)