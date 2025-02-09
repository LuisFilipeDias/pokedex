package com.ctw.ctwpokedex.presentation.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ctw.ctwpokedex.R
import com.ctw.ctwpokedex.helpers.openPokedexActivity
import com.ctw.ctwpokedex.presentation.viewmodel.OnboardingViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.compose.setContent

@AndroidEntryPoint
class OnboardingActivity : AppCompatActivity() {

    private val onboardingViewModel: OnboardingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (onboardingViewModel.hasUserLoggedIn()) {
            navigateToPokedex()
        } else {
            setContent {
                Card {
                    Text("Text")
                }
                // SimpleComposeScreen()
            }


            /*   setContentView(R.layout.onboarding_activity)
               findViewById<Button>(R.id.button_start).setOnClickListener {
                   onboardingViewModel.setUserLoggedIn()
                   navigateToPokedex()
               }*/
        }
    }

    private fun navigateToPokedex() = this.openPokedexActivity()
}


@Composable
fun SimpleComposeScreen() {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Counter: 0", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSimpleComposeScreen() {
    SimpleComposeScreen()
}