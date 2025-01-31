package com.ctw.ctwpokedex.presentation.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ctw.ctwpokedex.R
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
                onboardingViewModel.setKeyAccessed()
                navigateToPokedex()
            }
        }
    }

    private fun navigateToPokedex() {
        startActivity(Intent(this, PokedexActivity::class.java))
    }

}