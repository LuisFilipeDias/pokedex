package com.ctw.ctwpokedex.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ctw.ctwpokedex.data.database.PokedexDatabase
import com.ctw.ctwpokedex.data.models.converter.SpritesConverter

@Entity(tableName = PokedexDatabase.TABLE_POKEMON)
@TypeConverters(
    SpritesConverter::class
)
data class Pokemon(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int, // Make mutable for Room to handle changes

    @ColumnInfo(name = "name")
    var name: String, // Make mutable

    @ColumnInfo(name = "sprites")
    var sprites: Sprites?, // Make mutable

    @ColumnInfo(name = "sprite")
    var sprite: String, // Already mutable, no change needed

    @ColumnInfo(name = "weight")
    var weight: Int, // Make mutable

    @ColumnInfo(name = "height")
    var height: Int // Make mutable
) {
    constructor() : this(0, "", null, "", 0, 0) // Default constructor for Room

    fun getImage() = sprites?.other?.dream_world?.front_default.orEmpty()
}

data class Sprites(
    val other: Other
)

data class Other(
    val dream_world: DreamWorld
)

data class DreamWorld(
    val front_default: String
)
