package com.ctw.ctwpokedex

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.ctw.ctwpokedex.data.database.PokedexDatabase
import com.ctw.ctwpokedex.di.DispatcherModule
import com.ctw.ctwpokedex.di.ProviderModule
import dagger.BindsInstance
import dagger.Component
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Singleton

@Singleton
@Component(modules = [DispatcherModule::class])
interface PokedexComponent {
    fun injectApplication(application: PokedexApplication)

    @Component.Builder
    interface Builder {
        fun build(): PokedexComponent

        @BindsInstance
        fun app(app: Application): Builder
    }
}

@HiltAndroidApp
class PokedexApplication : Application() {

    companion object {
        lateinit var pokedexDatabase: PokedexDatabase
        const val POKEDEX_DB = "pikachu_db"
    }

    override fun onCreate() {
        DaggerPokedexComponent.builder().app(this).build().injectApplication(this)
        super.onCreate()

        pokedexDatabase = Room
            .databaseBuilder(this, PokedexDatabase::class.java, POKEDEX_DB)
            .fallbackToDestructiveMigration()
            .build()
        Log.d("TAG", "Hey im using your custom application class")
    }
}