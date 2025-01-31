package com.ctw.ctwpokedex.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ctw.ctwpokedex.data.dao.PokedexDao
import com.ctw.ctwpokedex.data.dao.PokemonDao
import com.ctw.ctwpokedex.data.models.PokedexItem
import com.ctw.ctwpokedex.data.models.Pokemon

@Database(
    version = 2,
    entities = [
        PokedexItem::class,
        Pokemon::class
    ])
abstract class PokedexDatabase : RoomDatabase() {

    abstract fun pokedexDao(): PokedexDao
    abstract fun pokemonDao(): PokemonDao

    companion object {
        const val TABLE_POKEDEX = "table_pokedex"
        const val TABLE_POKEMON = "table_pokemon"
    }
}