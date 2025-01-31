package com.ctw.ctwpokedex.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ctw.ctwpokedex.data.models.Pokemon
import com.ctw.ctwpokedex.domain.usecases.PokemonUseCase
import com.ctw.ctwpokedex.domain.usecases.TimestampUseCase
import com.ctw.ctwpokedex.presentation.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val pokemonUseCase: PokemonUseCase,
    private val timestampUseCase: TimestampUseCase
) : ViewModel() {

    private val _pokemonLiveData = MutableLiveData<UiState<Pokemon>>()
    val pokemonLiveData: LiveData<UiState<Pokemon>>
        get() = _pokemonLiveData

    fun getPokemon(pokemonName: String) {
        _pokemonLiveData.value = UiState.Loading
        viewModelScope.launch {
            kotlin.runCatching {
                val pokemon = pokemonUseCase.getPokemon(pokemonName)
                _pokemonLiveData.value = UiState.Display(pokemon)
            }.onFailure {
                Timber.i("Failed to get Pokemon due to $it")
                _pokemonLiveData.value =
                    UiState.Error(message = "Your Pokemon is not available right now...")
            }
        }
    }

    fun getFormattedTime(): String {
        val timeAndDate = timestampUseCase()

        return if (timeAndDate != null) {
            "Timestamp: ${"%02d".format(timeAndDate.hour)}:${
                "%02d".format(
                    timeAndDate.minute
                )
            } on ${timeAndDate.dayOfMonth} ${
                timeAndDate.month.name.lowercase()
                    .replaceFirstChar { it.uppercase() }
            }, ${timeAndDate.year}"
        } else {
            "Error, unable to provide timestamp"
        }
    }
}