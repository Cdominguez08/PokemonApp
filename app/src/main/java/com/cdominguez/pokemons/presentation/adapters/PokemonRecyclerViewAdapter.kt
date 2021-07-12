package com.cdominguez.pokemons.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.cdominguez.domain.Pokemon
import com.cdominguez.pokemons.R

class PokemonRecyclerViewAdapter(
    val onPokemonItemListener: OnPokemonItemListener
) :
    RecyclerView.Adapter<PokemonRecyclerViewAdapter.PokemonViewHolder>() {

    val list = mutableListOf<Pokemon>()

    inner class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvName = itemView.findViewById<TextView>(R.id.tvName)
        val cvItemContainer = itemView.findViewById<CardView>(R.id.cvItemContainer)

        fun updateUI(pokemon: Pokemon, position: Int){
            tvName.text = "$position. ${pokemon.name}"
            cvItemContainer.setOnClickListener {
                onPokemonItemListener.onItemClick(pokemon)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon_list,parent,false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.updateUI(list.elementAt(position), position+1)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updatePokemonList(updateList : List<Pokemon>){
        list.addAll(updateList)
        notifyDataSetChanged()
    }

    fun clearList() {
        list.clear()
        notifyDataSetChanged()
    }

    interface OnPokemonItemListener{
        fun onItemClick(pokemon: Pokemon)
    }
}