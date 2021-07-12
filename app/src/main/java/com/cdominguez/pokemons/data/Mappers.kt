package com.cdominguez.pokemons.data.network

import com.cdominguez.domain.Pokemon
import com.cdominguez.domain.PokemonDetail
import com.cdominguez.pokemons.data.local.entities.PokemonEntity
import com.cdominguez.pokemons.data.network.entities.PokemonDetailResponse
import com.cdominguez.pokemons.data.network.entities.PokemonResponse

fun PokemonResponse.toPokemon() = Pokemon(
    name,
    url
)

fun PokemonDetailResponse.toPokemonDetail() = PokemonDetail(
    id,
    name,
    sprites.other.officialArtWork.imageUrl,
    height,
    weight
)

fun PokemonEntity.toPokemonDetail() = PokemonDetail(
    id,
    name,
    imageUrl,
    height,
    weight
)

fun PokemonDetail.toPokemonEntity() = PokemonEntity(
    id,
    name,
    imageUrl,
    height,
    weight
)

fun List<PokemonEntity>.toPokemonDomainList() = map(PokemonEntity::toPokemonDetail)