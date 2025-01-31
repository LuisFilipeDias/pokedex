package com.ctw.ctwpokedex.presentation.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.ctw.ctwpokedex.R

class OnboardingActivity : AppCompatActivity() {

    private val sharedPref: SharedPreferences by lazy {
        this.getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(hasUserLoggedIn()) {
            navigateToPokedex()
        } else {
            // Show onboarding screen
            setContentView(R.layout.onboarding_activity)
            findViewById<Button>(R.id.button_start).setOnClickListener {
                with (sharedPref.edit()) {
                    putBoolean(KEY_HAS_ACCESSED, true)
                    apply()
                }
                navigateToPokedex()
            }
        }
    }

    private fun hasUserLoggedIn() = sharedPref.getBoolean(KEY_HAS_ACCESSED, false)

    private fun navigateToPokedex() {
        startActivity(Intent(this, PokedexActivity::class.java))
    }

    companion object {
        private const val SHARED_PREFERENCES_FILE_NAME = "pokedexSharedPreferences"
        private const val KEY_HAS_ACCESSED = "key_has_accessed"
    }
}