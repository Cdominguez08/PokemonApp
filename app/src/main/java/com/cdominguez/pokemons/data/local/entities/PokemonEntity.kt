package com.cdominguez.pokemons.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonEntity(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "name") val name : String,
    @ColumnInfo(name = "image_url") val imageUrl : String?,
    @ColumnInfo(name = "height") val height : Int,
    @ColumnInfo(name = "weight") val weight : Int
)
