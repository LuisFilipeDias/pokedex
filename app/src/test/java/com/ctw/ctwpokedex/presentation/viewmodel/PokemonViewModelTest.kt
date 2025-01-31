package com.ctw.ctwpokedex.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ctw.ctwpokedex.data.models.Pokemon
import com.ctw.ctwpokedex.domain.repositories.PokemonRepository
import com.ctw.ctwpokedex.domain.usecases.PokemonUseCase
import com.ctw.ctwpokedex.presentation.UiState
import com.ctw.ctwpokedex.util.UnitTestSchedulerProvider
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PokemonViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val schedulerProvider = UnitTestSchedulerProvider()

    @Mock
    private lateinit var pokemonUseCase: PokemonUseCase

    @Mock
    private lateinit var disposables: CompositeDisposable

    @Mock
    private lateinit var uiObserver: Observer<UiState<Pokemon>>

    private lateinit var underTest: PokemonViewModel

    private val pikachu = Pokemon(
        id = 25,
        name = "Pikachu",
        sprites = null,
        sprite = "some-url",
        weight = 0,
        height = 0
    )

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        underTest = PokemonViewModel(schedulerProvider, disposables, pokemonUseCase)
    }

    @Test
    fun `given repository returns ERROR then return Error UiState with proper message`() {
        val pokemonName = "pikachu"
        given(pokemonUseCase.getPokemon(pokemonName)).willReturn(Single.error(Throwable()))

        underTest.pokemonLiveData.observeForever(uiObserver)
        underTest.getPokemon(pokemonName)

        with(Mockito.inOrder(uiObserver)){
            verify(uiObserver).onChanged(UiState.Loading)
            verify(uiObserver).onChanged(UiState.Error(message = "Your Pokemon is not available right now..."))
        }
    }

    @Test
    fun `given repository returns SUCCESS then return Display UiState with data`() {
        val pokemonName = "pikachu"
        given(pokemonUseCase.getPokemon(pokemonName)).willReturn(Single.just(pikachu))

        underTest.pokemonLiveData.observeForever(uiObserver)
        underTest.getPokemon(pokemonName)

        with(Mockito.inOrder(uiObserver)){
            verify(uiObserver).onChanged(UiState.Loading)
            verify(uiObserver).onChanged(UiState.Display(pikachu))
        }
    }
}