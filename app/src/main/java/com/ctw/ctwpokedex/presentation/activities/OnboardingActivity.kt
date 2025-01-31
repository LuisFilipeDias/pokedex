package com.ctw.ctwpokedex.presentation.activities

import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ctw.ctwpokedex.R
import com.ctw.ctwpokedex.helpers.openPokedexActivity
import com.ctw.ctwpokedex.presentation.viewmodel.OnboardingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingActivity : AppCompatActivity() {

    private val onboardingViewModel: OnboardingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(onboardingViewModel.hasUserLoggedIn()) {
            navigateToPokedex()
        } else {
            setContentView(R.layout.onboarding_activity)
            findViewById<Button>(R.id.button_start).setOnClickListener {
                onboardingViewModel.setUserLoggedIn()
                navigateToPokedex()
            }
        }
    }

    private fun navigateToPokedex() = this.openPokedexActivity()
}