package com.ctw.ctwpokedex.domain.usecases

import com.ctw.ctwpokedex.data.models.PokedexItem
import com.ctw.ctwpokedex.data.repository.PokedexRepositoryImpl
import com.ctw.ctwpokedex.domain.repositories.PokedexRepository
import io.reactivex.Single

class PokedexUseCase(private val repository: PokedexRepository = PokedexRepositoryImpl()) {

    suspend fun getPokemons(): List<PokedexItem> = repository.getPokemons()
}