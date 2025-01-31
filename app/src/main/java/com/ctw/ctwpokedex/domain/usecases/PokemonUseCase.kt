package com.ctw.ctwpokedex.domain.usecases

import com.ctw.ctwpokedex.data.models.Pokemon
import com.ctw.ctwpokedex.data.repository.PokemonRepositoryImpl
import com.ctw.ctwpokedex.domain.repositories.PokemonRepository
import io.reactivex.Single

class PokemonUseCase(private val repository: PokemonRepository = PokemonRepositoryImpl()) {

    suspend fun getPokemon(pokemonName: String): Pokemon = repository.getPokemon(pokemonName)
}