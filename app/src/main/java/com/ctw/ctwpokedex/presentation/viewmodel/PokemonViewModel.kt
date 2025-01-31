package com.ctw.ctwpokedex.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ctw.ctwpokedex.data.models.Pokemon
import com.ctw.ctwpokedex.domain.usecases.PokemonUseCase
import com.ctw.ctwpokedex.presentation.UiState
import kotlinx.coroutines.launch

class PokemonViewModel(
    private val pokemonUseCase: PokemonUseCase = PokemonUseCase()
) : ViewModel() {

    private val _pokemonLiveData = MutableLiveData<UiState<Pokemon>>()
    val pokemonLiveData: LiveData<UiState<Pokemon>>
        get() = _pokemonLiveData

    fun getPokemon(pokemonName: String) {
        _pokemonLiveData.value = UiState.Loading
        viewModelScope.launch {
            try {
                val pokemon = pokemonUseCase.getPokemon(pokemonName)
                _pokemonLiveData.value = UiState.Display(pokemon)
            } catch (ex: Exception) {
                _pokemonLiveData.value = UiState.Error(message = "Your Pokemon is not available right now...")
            }
        }
    }
}