package com.ctw.ctwpokedex.domain.repositories

import com.ctw.ctwpokedex.data.models.PokedexItem
import io.reactivex.Single

interface PokedexRepository {
    suspend fun getPokemons(): List<PokedexItem>
}