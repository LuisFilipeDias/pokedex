package com.ctw.ctwpokedex.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ctw.ctwpokedex.data.models.PokedexItem
import com.ctw.ctwpokedex.domain.usecases.PokedexUseCase
import com.ctw.ctwpokedex.presentation.UiState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.verifyOrder
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PokedexViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule() // Allows LiveData to run synchronously

    private val testDispatcher = UnconfinedTestDispatcher() // Runs coroutines immediately

    @MockK
    private lateinit var pokedexUseCase: PokedexUseCase

    @MockK
    private lateinit var disposables: CompositeDisposable

    @MockK
    private lateinit var uiObserver: Observer<UiState<List<PokedexItem>>>

    //    @InjectMocks
    private lateinit var victim: PokedexViewModel

    private val pokemonList = listOf(
        PokedexItem(
            rowId = 0,
            image = "some-url",
            name = "Pikachu",
            url = "www.ufpa.br"
        ),
        PokedexItem(
            rowId = 1,
            image = "some-url",
            name = "Charmander",
            url = "www.ufpa.br"
        ),
        PokedexItem(
            rowId = 2,
            image = "some-url",
            name = "Dragonite",
            url = "www.ufpa.br"
        )
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `given repository throws then return Error UiState with proper message`() = runTest {
        coEvery { pokedexUseCase.getPokemons() } throws IllegalStateException("exception")

        victim = PokedexViewModel(pokedexUseCase)
        victim.pokedexLiveData.observeForever(uiObserver)
        victim.fetchPokedex()

        verifyOrder {
            uiObserver.onChanged(UiState.Loading)
            uiObserver.onChanged(UiState.Error(message = "We were unable to get Pokedex data at the moment. Exception java.lang.IllegalStateException: exception"))
        }
        victim.pokedexLiveData.removeObserver(uiObserver)
    }

    @Test
    fun `given repository returns empty list then return Error UiState with proper message`() = runTest {
        coEvery { pokedexUseCase.getPokemons() } returns emptyList()

        victim = PokedexViewModel(pokedexUseCase)
        victim.pokedexLiveData.observeForever(uiObserver)
        victim.fetchPokedex()

        verifyOrder {
            uiObserver.onChanged(UiState.Loading)
            uiObserver.onChanged(UiState.Error(message = "No pokemons were found."))
        }
        victim.pokedexLiveData.removeObserver(uiObserver)
    }

    @Test
    fun `given repository returns SUCCESS then return Display UiState with data`() = runTest {
        coEvery { pokedexUseCase.getPokemons() } returns pokemonList

        victim = PokedexViewModel(pokedexUseCase)
        victim.pokedexLiveData.observeForever(uiObserver)
        victim.fetchPokedex()

        verifyOrder {
            uiObserver.onChanged(UiState.Loading)
            uiObserver.onChanged(UiState.Display(pokemonList))
        }
        victim.pokedexLiveData.removeObserver(uiObserver)
    }
}