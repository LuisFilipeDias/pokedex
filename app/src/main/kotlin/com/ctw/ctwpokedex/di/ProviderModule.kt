/*
 * Copyright (c) BMW Critical TechWorks. All rights reserved.
 */
package com.ctw.ctwpokedex.di

import android.content.Context
import android.content.SharedPreferences
import com.ctw.ctwpokedex.PokedexApplication
import com.ctw.ctwpokedex.data.RetrofitModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ProviderModule {

    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.createDeviceProtectedStorageContext()
            .getSharedPreferences("pokedexSharedPreferences", Context.MODE_PRIVATE)

    @Provides
    fun providePokedexApi() = RetrofitModule.pokedexApi

    @Provides
    fun providePokemonDao() = PokedexApplication.pokedexDatabase.pokemonDao()

    @Provides
    fun providePokedexDao() = PokedexApplication.pokedexDatabase.pokedexDao()

}
