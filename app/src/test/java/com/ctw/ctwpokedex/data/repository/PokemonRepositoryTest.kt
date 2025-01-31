package com.ctw.ctwpokedex.data.repository

import com.ctw.ctwpokedex.data.PokedexApi
import com.ctw.ctwpokedex.data.dao.PokemonDao
import com.ctw.ctwpokedex.domain.repositories.PokemonRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PokemonRepositoryTest {

    @Mock
    private lateinit var api: PokedexApi

    @Mock
    private lateinit var pokemonDao: PokemonDao

    private lateinit var underTest: PokemonRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        underTest = PokemonRepositoryImpl(
            api = api,
            pokemonDao = pokemonDao
        )
    }

    /*
     Useful mockito methods:
     given - to make a mock return what you want it to return
     verify - to verify something happened
     */

    @Test
    fun `GIVEN api returns valid results THEN return pokemon list`() {
        //given

        //when

        //then
    }

    @Test
    fun `GIVEN api throws error AND database returns valid result THEN get pokemon list from database`() {
        //given

        //when

        //then
    }

    @Test
    fun `GIVEN api throws error AND database returns error THEN return error`() {
        //given

        //when

        //then
    }
}