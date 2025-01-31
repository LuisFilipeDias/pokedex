package com.ctw.ctwpokedex.data.repository

import com.ctw.ctwpokedex.data.PokedexApi
import com.ctw.ctwpokedex.data.dao.PokemonDao
import com.ctw.ctwpokedex.data.models.Pokemon
import com.ctw.ctwpokedex.di.IoDispatcher
import com.ctw.ctwpokedex.domain.repositories.PokemonRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokedexApi: PokedexApi,
    private val pokemonDao: PokemonDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
): PokemonRepository {

    override suspend fun getPokemon(pokemonName: String): Pokemon {
        return try {
            val pokemon = pokedexApi.getPokemon(pokemonName)
            CoroutineScope(dispatcher).launch {
                pokemonDao.insertPokemon(pokemon)
            }
            pokemon
        } catch (ex: Exception) {
            pokemonDao.getPokemonByName(pokemonName)
        }
    }
}