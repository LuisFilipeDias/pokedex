package com.ctw.ctwpokedex.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ctw.ctwpokedex.data.database.PokedexDatabase
import com.ctw.ctwpokedex.data.models.PokedexItem

@Dao
interface PokedexDao {

    @Query("SELECT * FROM ${PokedexDatabase.TABLE_POKEDEX}")
    fun getPokedex() : List<PokedexItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokedex(pokedex: List<PokedexItem>)
}
