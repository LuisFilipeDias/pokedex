package com.ctw.ctwpokedex.domain.repositories

import com.ctw.ctwpokedex.data.models.Pokemon
import io.reactivex.Single

interface PokemonRepository {
    suspend fun getPokemon(pokemonName: String): Pokemon
}