package com.ctw.ctwpokedex.presentation.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ctw.ctwpokedex.R
import com.ctw.ctwpokedex.data.models.PokedexItem
import com.ctw.ctwpokedex.presentation.activities.PokedexClickListener

class PokedexItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val pokemonName: TextView = itemView.findViewById(R.id.txt_pokemon_name)
    private val pokemonImage: ImageView = itemView.findViewById(R.id.pokemon_image)
    private val pokedexItem: CardView = itemView.findViewById(R.id.pokedex_item)

    fun bind(item: PokedexItem, clickListener: PokedexClickListener) {
        pokedexItem.setOnClickListener {
            clickListener.click(item.name, item.image)
        }

        Glide.with(itemView.context)
            .load(item.image)
            .into(pokemonImage)

        pokemonName.text = item.name
    }
}