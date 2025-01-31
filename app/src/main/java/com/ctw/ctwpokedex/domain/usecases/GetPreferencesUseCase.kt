package com.ctw.ctwpokedex.domain.usecases

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPreferencesUseCase @Inject constructor(
    @ApplicationContext context: Context,
    private val sharedPreferences: SharedPreferences
) {

    operator fun <T> set(key: String, value: T) {
        with(sharedPreferences.edit()) {
            when (value) {
                is Int -> putInt(key, value)
                is Long -> putLong(key, value)
                is Float -> putFloat(key, value)
                is Boolean -> putBoolean(key, value)
                is String -> putString(key, value)
            }
            apply()
        }
    }

    fun <T : Any> get(key: String, defaultValue: T): T {
        return when (defaultValue) {
            is Int -> sharedPreferences.getInt(key, defaultValue as Int) as T
            is Long -> sharedPreferences.getLong(key, defaultValue as Long) as T
            is Float -> sharedPreferences.getFloat(key, defaultValue as Float) as T
            is Boolean -> sharedPreferences.getBoolean(key, defaultValue as Boolean) as T
            is String -> sharedPreferences.getString(key, defaultValue as String) as T
            else -> defaultValue
        }
    }
}
