package com.ctw.ctwpokedex.data.repository

import com.ctw.ctwpokedex.data.PokedexApi
import com.ctw.ctwpokedex.data.dao.PokedexDao
import com.ctw.ctwpokedex.data.models.PokedexItem
import com.ctw.ctwpokedex.data.models.PokedexItems
import com.ctw.ctwpokedex.domain.repositories.PokedexRepository
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PokedexRepositoryTest {

    @Mock
    private lateinit var api: PokedexApi

    @Mock
    private lateinit var pokedexDao: PokedexDao

    private lateinit var underTest: PokedexRepository

    private val pokemonList = listOf(
        PokedexItem(
            rowId = 0,
            image = "https://pokeapi.co/api/v2/pokemon/1/",
            name = "Pikachu",
            url = "/pokemon/1"
        ),
        PokedexItem(
            rowId = 1,
            image = "https://pokeapi.co/api/v2/pokemon/1/",
            name = "Charmander",
            url = "/pokemon/1"
        ),
        PokedexItem(
            rowId = 2,
            image = "https://pokeapi.co/api/v2/pokemon/1/",
            name = "Dragonite",
            url = "/pokemon/1"
        )
    )

    private val pokedex_items = PokedexItems(pokemonList)

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        underTest = PokedexRepositoryImpl(
            api = api,
            pokedexDao = pokedexDao
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
        given(api.listFirstGeneration()).willReturn(Single.just(pokedex_items))

        underTest.getPokemons()
            .test()
            .assertValue(pokedex_items.results)

        verify(pokedexDao, times(1)).insertPokedex(pokedex_items.results)
    }

    @Test
    fun `GIVEN api throws error AND database returns valid result THEN get pokemon list from database`() {
        //given
        given(api.listFirstGeneration()).willReturn(Single.error(Throwable()))
        given(api.listFirstGeneration()).willAnswer{pokedexDao.getPokedex()}
        given(pokedexDao.getPokedex()).willReturn(Single.just(pokemonList))

        underTest.getPokemons()

        verify(pokedexDao, times(1)).getPokedex()
    }

    @Test
    fun `GIVEN api throws error AND database returns error THEN return error`() {
        //given

        //when

        //then
    }
}