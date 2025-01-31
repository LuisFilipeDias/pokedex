package com.ctw.ctwpokedex.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ctw.ctwpokedex.data.models.PokedexItem
import com.ctw.ctwpokedex.domain.repositories.PokedexRepository
import com.ctw.ctwpokedex.domain.usecases.PokedexUseCase
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
import org.mockito.Mockito.inOrder
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PokedexViewmodelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val schedulerProvider = UnitTestSchedulerProvider()

    @Mock
    private lateinit var pokedexUseCase: PokedexUseCase

    @Mock
    private lateinit var disposables: CompositeDisposable

    @Mock
    private lateinit var uiObserver: Observer<UiState<List<PokedexItem>>>

    private lateinit var underTest: PokedexViewmodel

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

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `given repository returns ERROR then return Error UiState with proper message`() {
    }

    @Test
    fun `given repository returns SUCCESS then return Display UiState with data`() {
    }
}