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
    spritesResponse.other.officialArtWork.imageUrl ?: "",
    height,
    weight,
    detailUrl ?: ""
)

fun PokemonDetail.toPokemonEntity() = PokemonEntity(
    id,
    name,
    imageUrl,
    height,
    weight,
    detailUrl
)

fun PokemonEntity.toPokemonDetail() = PokemonDetail(
    id,
    name,
    imageUrl,
    height,
    weight,
    detailUrl
)

fun PokemonEntity.toPokemon() = Pokemon(
    name,
    detailUrl
)

fun List<PokemonEntity>.toPokemonDomainList() = map(PokemonEntity::toPokemon)

