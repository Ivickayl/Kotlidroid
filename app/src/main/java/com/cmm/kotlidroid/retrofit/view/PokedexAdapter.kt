package com.cmm.kotlidroid.retrofit.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cmm.kotlidroid.R
import com.cmm.kotlidroid.retrofit.model.Pokemon
import com.cmm.kotlidroid.retrofit.view.PokedexAdapter.PokedexViewHolder

class PokedexAdapter(private val context: Context) :
    RecyclerView.Adapter<PokedexViewHolder>() {

    private val pokedexList: MutableList<Pokemon> = mutableListOf()
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val onClickCallback = context as OnClickCallBack

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokedexViewHolder {
        return PokedexViewHolder(
            inflater.inflate(
                R.layout.activity_retrofit_pokemon_mono_type_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PokedexViewHolder, position: Int) {
        val currentItem = pokedexList[position]

        holder.pokemonId.text = currentItem.pokemonId
        holder.pokemonName.text = currentItem.name

        Glide.with(context)
            .load(currentItem.sprites.frontDefault)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.pokemonIcon)
    }

    override fun getItemCount(): Int {
        return pokedexList.size
    }

    fun rebuild(pokedex: List<Pokemon>) {
        pokedexList.clear()
        pokedexList.addAll(pokedex)

        this.notifyDataSetChanged()
    }

    fun getItemAt(pos: Int): Pokemon {
        return pokedexList[pos]
    }

    fun deleteItem(pos: Int) {
        pokedexList.removeAt(pos)
        notifyItemRemoved(pos)
    }

    inner class PokedexViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pokemonId: TextView = itemView.findViewById(R.id.pokemon_mono_type_id)
        val pokemonName: TextView = itemView.findViewById(R.id.pokemon_mono_type_name)
        val pokemonIcon: ImageView = itemView.findViewById(R.id.pokemon_mono_type_icon)

        private fun itemClicked() {

            onClickCallback.onItemClick(
                this@PokedexAdapter.pokedexList[adapterPosition]
            )
        }

        init {
            this.itemView.setOnClickListener {
                itemClicked()
            }
        }
    }
}