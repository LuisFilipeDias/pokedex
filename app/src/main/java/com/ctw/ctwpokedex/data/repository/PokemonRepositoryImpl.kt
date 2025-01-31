package com.ctw.ctwpokedex.data.repository

import com.ctw.ctwpokedex.PokedexApplication
import com.ctw.ctwpokedex.data.PokedexApi
import com.ctw.ctwpokedex.data.RetrofitModule
import com.ctw.ctwpokedex.data.dao.PokemonDao
import com.ctw.ctwpokedex.data.models.Pokemon
import com.ctw.ctwpokedex.domain.repositories.PokemonRepository

class PokemonRepositoryImpl(
    private val api: PokedexApi = RetrofitModule().pokedexApi,
    private val pokemonDao: PokemonDao = PokedexApplication.pokedexDatabase.pokemonDao()
): PokemonRepository {

    override suspend fun getPokemon(pokemonName: String): Pokemon {
        return try {
            val pokemon = api.getPokemon(pokemonName)
            pokemonDao.insertPokemon(pokemon)
            pokemon
        } catch (ex: Exception) {
            pokemonDao.getPokemonByName(pokemonName)
        }
    }
}