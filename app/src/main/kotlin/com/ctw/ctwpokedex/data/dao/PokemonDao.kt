package com.ctw.ctwpokedex.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ctw.ctwpokedex.data.database.PokedexDatabase
import com.ctw.ctwpokedex.data.models.Pokemon

@Dao
interface PokemonDao {

    @Query("SELECT * FROM ${PokedexDatabase.TABLE_POKEMON} WHERE name = :pokemonName")
    fun getPokemonByName(pokemonName: String) : Pokemon

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemon(pokemon: Pokemon)

}