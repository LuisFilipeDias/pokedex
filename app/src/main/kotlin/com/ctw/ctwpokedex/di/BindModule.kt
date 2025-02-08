/*
 * Copyright (c) BMW Critical TechWorks. All rights reserved.
 */
package com.ctw.ctwpokedex.di

import com.ctw.ctwpokedex.data.repository.PokedexRepositoryImpl
import com.ctw.ctwpokedex.data.repository.PokemonRepositoryImpl
import com.ctw.ctwpokedex.domain.repositories.PokedexRepository
import com.ctw.ctwpokedex.domain.repositories.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BindModule {

    @Binds
    abstract fun providePokemonRepository(
        pokemonRepositoryImpl: PokemonRepositoryImpl
    ): PokemonRepository

    @Binds
    abstract fun providePokedexRepository(
        pokedexRepositoryImpl: PokedexRepositoryImpl
    ): PokedexRepository

}
