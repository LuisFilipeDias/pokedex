package com.ctw.ctwpokedex.data.repository

import com.ctw.ctwpokedex.PokedexApplication
import com.ctw.ctwpokedex.data.PokedexApi
import com.ctw.ctwpokedex.data.RetrofitModule
import com.ctw.ctwpokedex.data.dao.PokedexDao
import com.ctw.ctwpokedex.data.models.PokedexItem
import com.ctw.ctwpokedex.domain.repositories.PokedexRepository
import io.reactivex.Single

class PokedexRepositoryImpl(
    private val api: PokedexApi = RetrofitModule().pokedexApi,
    private val pokedexDao: PokedexDao = PokedexApplication.pokedexDatabase.pokedexDao()
): PokedexRepository {

    override suspend fun getPokemons(): List<PokedexItem> {
        return try {
            val pokemons = api.listFirstGeneration().results.map { pokedexItem ->
                pokedexItem.copy(
                    image = pokedexItem.mountImage()
                )
            }
           // pokedexDao.insertPokedex(pokemons)
            pokemons
        } catch (ex: Exception) {
            pokedexDao.getPokedex()
        }
    }
}