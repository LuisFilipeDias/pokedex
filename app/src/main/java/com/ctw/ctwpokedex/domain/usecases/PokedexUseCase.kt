package com.ctw.ctwpokedex.domain.usecases

import com.ctw.ctwpokedex.data.models.PokedexItem
import com.ctw.ctwpokedex.data.repository.PokedexRepositoryImpl
import com.ctw.ctwpokedex.domain.repositories.PokedexRepository
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokedexUseCase @Inject constructor(private val repository: PokedexRepository) {

    suspend fun getPokemons(): List<PokedexItem> = repository.getPokemons()
}