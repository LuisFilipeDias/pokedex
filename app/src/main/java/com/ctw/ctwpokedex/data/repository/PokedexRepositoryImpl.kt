package com.ctw.ctwpokedex.data.repository

import com.ctw.ctwpokedex.data.PokedexApi
import com.ctw.ctwpokedex.data.dao.PokedexDao
import com.ctw.ctwpokedex.data.models.PokedexItem
import com.ctw.ctwpokedex.di.DefaultDispatcher
import com.ctw.ctwpokedex.di.IoDispatcher
import com.ctw.ctwpokedex.domain.repositories.PokedexRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokedexRepositoryImpl @Inject constructor(
    private val pokedexApi: PokedexApi,
    private val pokedexDao: PokedexDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
): PokedexRepository {

    override suspend fun getPokemons(): List<PokedexItem> {
        return try {
            val pokemons = pokedexApi.listFirstGeneration().results.map { pokedexItem ->
                pokedexItem.copy(
                    image = pokedexItem.mountImage()
                )
            }
            CoroutineScope(dispatcher).launch {
                pokedexDao.insertPokedex(pokemons)
            }
            pokemons
        } catch (ex: Exception) {
            Timber.i("Failed to insert to Pokedex due to $ex")
            pokedexDao.getPokedex()
        }
    }
}