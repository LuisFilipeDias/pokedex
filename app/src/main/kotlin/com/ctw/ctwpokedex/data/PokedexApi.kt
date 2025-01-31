package com.ctw.ctwpokedex.data

import com.ctw.ctwpokedex.data.models.PokedexItems
import com.ctw.ctwpokedex.data.models.Pokemon
import retrofit2.http.GET
import retrofit2.http.Path

interface PokedexApi {

    @GET("pokemon?limit=151")
    suspend fun listFirstGeneration() : PokedexItems

    @GET("pokemon/{pokemonName}")
    suspend fun getPokemon(@Path("pokemonName") pokemonName: String) : Pokemon
}