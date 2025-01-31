package com.ctw.ctwpokedex.domain.usecases

import com.ctw.ctwpokedex.data.models.Pokemon
import com.ctw.ctwpokedex.domain.repositories.PokemonRepository
import javax.inject.Inject

class PokemonUseCase @Inject constructor(private val repository: PokemonRepository) {

    suspend fun getPokemon(pokemonName: String): Pokemon = repository.getPokemon(pokemonName)
}