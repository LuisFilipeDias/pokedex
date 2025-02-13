package com.ctw.ctwpokedex.presentation.activities

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.constraintlayout.widget.ConstraintLayout
import com.ctw.ctwpokedex.R
import com.ctw.ctwpokedex.presentation.UiState
import com.ctw.ctwpokedex.presentation.activities.PokedexActivity.Companion.POKEMON_IMAGE_EXTRA
import com.ctw.ctwpokedex.presentation.activities.PokedexActivity.Companion.POKEMON_NAME_EXTRA
import com.ctw.ctwpokedex.presentation.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonActivity : AppCompatActivity() {

    private var pokemonName: String = ""
    private lateinit var pokemonImage: ImageView
    private lateinit var pokemonHeight: TextView
    private lateinit var pokemonWeight: TextView
    private lateinit var pokemonTimestamp: TextView
    private lateinit var progressBar: ConstraintLayout
    private lateinit var errorView: ConstraintLayout
    private lateinit var errorTextView: TextView
    private lateinit var retryButton: Button

    private lateinit var pokemonNameExtra: String
    private lateinit var pokemonUrlExtra: String

    private val viewModel: PokemonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pokemon_activity)
        pokemonNameExtra = intent.extras?.getString(POKEMON_NAME_EXTRA) ?: ""
        pokemonUrlExtra = intent.extras?.getString(POKEMON_IMAGE_EXTRA) ?: ""

        setContent {
            MaterialTheme {
                PokemonDetails(pokemonName)
            }
        }

        //  initViews()
        // initClicks()
        initObservers()
    }

    private fun initViews() {
        pokemonImage = findViewById(R.id.pokemon_image)
        //pokemonName = findViewById(R.id.pokemon_name)
        pokemonHeight = findViewById(R.id.pokemon_height)
        pokemonWeight = findViewById(R.id.pokemon_weight)
        pokemonTimestamp = findViewById(R.id.pokemon_timestamp)
        progressBar = findViewById(R.id.pokedex_progress)
        errorView = findViewById(R.id.error_view)
        errorTextView = findViewById(R.id.error_text)
        retryButton = findViewById(R.id.retry_button)
    }

    private fun initClicks() {
        /*retryButton.setOnClickListener {
            viewModel.getPokemon(pokemonNameExtra)
        }*/
    }

    private fun initObservers() {
        viewModel.pokemonLiveData.observe(this) { it ->
            when (it) {
                is UiState.Display -> {
                    //    progressBar.visibility = View.GONE
                    //    errorView.visibility = View.GONE
                    //                  Glide.with(this)
//                        .load(pokemonUrlExtra)
//                        .into(pokemonImage)
                    pokemonName = "Name: ${it.data.name}"
//                    pokemonHeight.text = "Height: ${it.data.height}"
//                    pokemonWeight.text = "Weight: ${it.data.weight}"
//                    pokemonTimestamp.text = viewModel.getFormattedTime()
                }

                is UiState.Error -> {
//                    progressBar.visibility = View.GONE
//                    errorView.visibility = View.VISIBLE
//                    errorTextView.text = it.message
                }

                UiState.Loading -> {
//                    errorView.visibility = View.GONE
//                    progressBar.visibility = View.VISIBLE
                }
            }
        }
        viewModel.getPokemon(pokemonNameExtra)
    }

}
