package com.ctw.ctwpokedex.presentation.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ctw.ctwpokedex.R
import com.ctw.ctwpokedex.presentation.UiState
import com.ctw.ctwpokedex.presentation.adapters.PokedexAdapter
import com.ctw.ctwpokedex.presentation.viewmodel.PokedexViewmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokedexActivity : AppCompatActivity() {

    private lateinit var pokedexRecyclerView: RecyclerView
    private lateinit var progressBar: ConstraintLayout
    private lateinit var errorView: ConstraintLayout
    private lateinit var errorTextView: TextView
    private lateinit var retryButton: Button

    private val viewModel: PokedexViewmodel by viewModels()

    private val clickListener = object : PokedexClickListener {
        override fun click(pokemonName: String, url: String) {
            val intent = Intent(this@PokedexActivity, PokemonActivity::class.java).apply {
                putExtra(POKEMON_NAME_EXTRA, pokemonName)
                putExtra(POKEMON_IMAGE_EXTRA, url)
            }

            this@PokedexActivity.startActivity(intent)
        }
    }

    private val pokedexAdapter by lazy {
        PokedexAdapter(clickListener)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pokedex_activity)
        Glide.with(this)
            .load("https://static.wikia.nocookie.net/pokepediabr/images/3/38/" +
                    "Pok%C3%A9dex_Kanto.png/revision/latest/scale-to-width-down/276?" +
                    "cb=20131224014121&path-prefix=pt-br")
            .into(findViewById(R.id.pokedex_image))

        progressBar = findViewById(R.id.pokedex_progress)
        errorView = findViewById(R.id.error_view)
        errorTextView = findViewById(R.id.error_text)
        retryButton = findViewById(R.id.retry_button)

        initClicks()
        initRecyclerView()
        initObservers()
    }

    private fun initClicks(){
        retryButton.setOnClickListener {
            viewModel.fetchPokedex()
        }
    }

    private fun initRecyclerView() {
        pokedexRecyclerView = findViewById(R.id.pokedex_recyclerview)
        with(pokedexRecyclerView) {
            adapter = pokedexAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun initObservers() {
        viewModel.pokedexLiveData.observe(this) {
            when (it) {
                is UiState.Display -> {
                    progressBar.visibility = View.GONE
                    errorView.visibility = View.GONE
                    pokedexRecyclerView.visibility = View.VISIBLE
                    pokedexAdapter.submitList(it.data)
                }

                is UiState.Error -> {
                    progressBar.visibility = View.GONE
                    errorView.visibility = View.VISIBLE
                    errorTextView.text = it.message
                }

                UiState.Loading -> {
                    pokedexRecyclerView.visibility = View.GONE
                    errorView.visibility = View.GONE
                    progressBar.visibility = View.VISIBLE
                }

                else -> {}
            }
        }
        viewModel.fetchPokedex()
    }

    companion object {
        const val POKEMON_NAME_EXTRA = "pokemonName"
        const val POKEMON_IMAGE_EXTRA = "pokemonImage"
    }
}