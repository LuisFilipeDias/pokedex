package com.ctw.ctwpokedex.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.ctw.ctwpokedex.R
import com.ctw.ctwpokedex.data.models.PokedexItem
import com.ctw.ctwpokedex.presentation.activities.PokedexClickListener
import com.ctw.ctwpokedex.presentation.viewholders.PokedexItemViewHolder
import javax.inject.Inject

class PokedexAdapter @Inject constructor(private val clickListener: PokedexClickListener) :
    ListAdapter<PokedexItem, PokedexItemViewHolder>(PokemonItemDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PokedexItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.pokedex_item_view_holder, parent, false)
        )

    override fun onBindViewHolder(holder: PokedexItemViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }
}

class PokemonItemDiffUtil : DiffUtil.ItemCallback<PokedexItem>() {
    override fun areItemsTheSame(oldItem: PokedexItem, newItem: PokedexItem) =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: PokedexItem, newItem: PokedexItem) =
        oldItem.name == newItem.name
}