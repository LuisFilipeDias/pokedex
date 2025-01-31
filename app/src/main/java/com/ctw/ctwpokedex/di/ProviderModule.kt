/*
 * Copyright (c) BMW Critical TechWorks. All rights reserved.
 */
package com.ctw.ctwpokedex.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProviderModule {

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.createDeviceProtectedStorageContext()
            .getSharedPreferences("pokedexSharedPreferences", Context.MODE_PRIVATE)

}
