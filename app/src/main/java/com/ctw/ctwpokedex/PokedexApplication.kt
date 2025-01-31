package com.ctw.ctwpokedex

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.ctw.ctwpokedex.data.database.PokedexDatabase

class PokedexApplication : Application() {

    companion object {
        lateinit var pokedexDatabase: PokedexDatabase
        const val POKEDEX_DB = "pikachu_db"
    }

    override fun onCreate() {
        super.onCreate()

        pokedexDatabase = Room
            .databaseBuilder(this, PokedexDatabase::class.java, POKEDEX_DB)
            .fallbackToDestructiveMigration()
            .build()
        Log.d("TAG", "Hey im using your custom application class")
    }
}