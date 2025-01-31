package com.ctw.ctwpokedex.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.ctw.ctwpokedex.domain.usecases.GetPreferencesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val getPreferencesUseCase: GetPreferencesUseCase
): ViewModel() {

    fun setUserLoggedIn() {
        getPreferencesUseCase[KEY_HAS_ACCESSED] = true
    }

    fun hasUserLoggedIn() : Boolean {
        return getPreferencesUseCase.get(KEY_HAS_ACCESSED, false)
    }

    companion object {
        const val KEY_HAS_ACCESSED = "key_has_accessed"
    }

}