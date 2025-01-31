package com.ctw.ctwpokedex.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ctw.ctwpokedex.data.database.PokedexDatabase

@Entity(tableName = PokedexDatabase.TABLE_POKEDEX)
data class PokedexItem(
    @PrimaryKey(autoGenerate = true)
    val rowId: Int,  // Room will automatically generate the primary key
    @ColumnInfo(name = "image")
    var image: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "url")
    val url: String
) {
    fun mountImage(): String {
        val id = getPokemonId(url)
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
    }

    private fun getPokemonId(url: String) =
        url.split("/pokemon/").getOrNull(1)?.replace("/", "").orEmpty()
}