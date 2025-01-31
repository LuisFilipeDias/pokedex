/*
 * Copyright (c) BMW Critical TechWorks. All rights reserved.
 */
package com.ctw.ctwpokedex.di

import android.content.Context
import android.content.SharedPreferences
import com.ctw.ctwpokedex.PokedexApplication
import com.ctw.ctwpokedex.data.PokedexApi
import com.ctw.ctwpokedex.data.RetrofitModule
import com.ctw.ctwpokedex.data.dao.PokedexDao
import com.ctw.ctwpokedex.data.dao.PokemonDao
import com.ctw.ctwpokedex.data.repository.PokedexRepositoryImpl
import com.ctw.ctwpokedex.data.repository.PokemonRepositoryImpl
import com.ctw.ctwpokedex.domain.repositories.PokedexRepository
import com.ctw.ctwpokedex.domain.repositories.PokemonRepository
import com.ctw.ctwpokedex.domain.usecases.PokedexUseCase
import com.ctw.ctwpokedex.domain.usecases.PokemonUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProviderModule {

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.createDeviceProtectedStorageContext()
            .getSharedPreferences("pokedexSharedPreferences", Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun providePokedexApi() = RetrofitModule.pokedexApi

    @Singleton
    @Provides
    fun providePokemonDao() = PokedexApplication.pokedexDatabase.pokemonDao()

    @Singleton
    @Provides
    fun providePokedexDao() = PokedexApplication.pokedexDatabase.pokedexDao()

    @Singleton
    @Provides
    fun providePokemonUseCase(repository: PokemonRepository) = PokemonUseCase(repository)

    @Singleton
    @Provides
    fun providePokedexUseCase(pokedexRepository: PokedexRepository): PokedexUseCase =
        PokedexUseCase(pokedexRepository)

    @Singleton
    @Provides
    fun providePokemonRepository(
        pokedexApi: PokedexApi, pokemonDao: PokemonDao,
        @DefaultDispatcher dispatcher: CoroutineDispatcher
    ): PokemonRepository =
        PokemonRepositoryImpl(pokedexApi, pokemonDao, dispatcher)

    @Singleton
    @Provides
    fun providePokedexRepository(
        pokedexApi: PokedexApi, pokedexDao: PokedexDao,
        @DefaultDispatcher dispatcher: CoroutineDispatcher
    ): PokedexRepository =
        PokedexRepositoryImpl(pokedexApi, pokedexDao, dispatcher)

}
