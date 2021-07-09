package com.cdominguez.pokemons

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cdominguez.domain.Pokemon
import com.squareup.picasso.Picasso

class PokemonRecyclerViewAdapter :
    RecyclerView.Adapter<PokemonRecyclerViewAdapter.PokemonViewHolder>() {

    val list = mutableListOf<Pokemon>()

    inner class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivImage = itemView.findViewById<ImageView>(R.id.ivImage)
        val tvName = itemView.findViewById<TextView>(R.id.tvName)

        fun updateUI(pokemon: Pokemon){
            tvName.text = pokemon.name
            Picasso.get().load(pokemon.imageUrl)
                .placeholder(R.drawable.ic_baseline_image_24)
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(ivImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon_list,parent,false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.updateUI(list.elementAt(position))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updatePokemonList(updateList : List<Pokemon>){
        list.clear()
        list.addAll(updateList)
        notifyDataSetChanged()
    }
}