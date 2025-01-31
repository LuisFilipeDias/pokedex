package com.ctw.ctwpokedex.data

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitModule {

    val pokedexApi by lazy {
        initPokedexApi()
    }

    private val okHttpClient : OkHttpClient by lazy {
        val builder = OkHttpClient().newBuilder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .retryOnConnectionFailure(false)
        builder.build()
    }

    private fun initPokedexApi() : PokedexApi =
        adapter("https://pokeapi.co/api/v2/").create(PokedexApi::class.java)

    private fun adapter(endPoint : String) : Retrofit {
        val builder = Retrofit.Builder()
            .baseUrl(endPoint)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
        return builder.build()
    }

    companion object {
        private const val TIMEOUT = 10L
    }
}