package com.ctw.ctwpokedex.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ctw.ctwpokedex.data.models.PokedexItem
import com.ctw.ctwpokedex.domain.usecases.PokedexUseCase
import com.ctw.ctwpokedex.presentation.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokedexViewModel @Inject constructor(
    private val pokedexUseCase: PokedexUseCase
) : ViewModel() {

    private val _pokedexLiveData = MutableLiveData<UiState<List<PokedexItem>>>()
    val pokedexLiveData: LiveData<UiState<List<PokedexItem>>>
        get() = _pokedexLiveData

    fun fetchPokedex() {
        viewModelScope.launch {
            _pokedexLiveData.value = UiState.Loading
            runCatching {
                val pokemons = pokedexUseCase.getPokemons()
                if (pokemons.isEmpty()) {
                    _pokedexLiveData.value = UiState.Error(message = "No pokemons were found.")
                } else {
                    _pokedexLiveData.value = UiState.Display(pokemons)
                }
            }.onFailure {
                _pokedexLiveData.value =
                    UiState.Error(message = "We were unable to get Pokedex data at the moment. Exception $it")
            }
        }
    }
}