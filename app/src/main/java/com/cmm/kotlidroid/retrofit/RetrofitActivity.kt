package com.cmm.kotlidroid.retrofit

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cmm.kotlidroid.R
import com.cmm.kotlidroid.retrofit.model.Pokemon
import com.cmm.kotlidroid.retrofit.view.OnClickCallBack
import com.cmm.kotlidroid.retrofit.view.PokedexAdapter
import com.cmm.kotlidroid.retrofit.view.SwipeToDelete
import com.cmm.kotlidroid.retrofit.viewmodel.PokemonViewModel
import kotlinx.android.synthetic.main.activity_retrofit.*

class RetrofitActivity : AppCompatActivity(), OnClickCallBack {

    private lateinit var adapter: PokedexAdapter
    private lateinit var pokemonViewModel: PokemonViewModel
    private var pokemonObserver = Observer<List<Pokemon>> {
        updateRecyclerView(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        pokemonViewModel = ViewModelProvider(this)[PokemonViewModel::class.java]

        adapter = PokedexAdapter(this)
        pokedex_recycler_view.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        pokedex_recycler_view.adapter = adapter

        //Swipe to delete
        val itemTouchHelper = ItemTouchHelper(SwipeToDelete(adapter, pokemonViewModel))
        itemTouchHelper.attachToRecyclerView(pokedex_recycler_view)
    }

    override fun onStart() {
        super.onStart()
        pokemonViewModel.pokedexMutableLiveData.observe(this, pokemonObserver)
    }

    override fun onStop() {
        super.onStop()
        pokemonViewModel.pokedexMutableLiveData.removeObserver(pokemonObserver)
    }

    fun add(view: View) {
        val pokemonName: String = edit_text_search_pokemon.text.toString()

        if (pokemonName.isEmpty()) {
            Toast.makeText(this, "You entered an invalid name", Toast.LENGTH_SHORT).show()
        }

        //pokemonViewModel.insert(pokemonName)

        pokemonViewModel.fetchPokemon(pokemonName.toLowerCase())

    }

    fun deleteAll(view: View) {
        pokemonViewModel.deleteAll()
    }

    private fun updateRecyclerView(pokemonList: List<Pokemon>) {
        adapter.rebuild(pokemonList)
    }

    override fun onItemClick(itemClicked: Pokemon) {
        startActivity(Intent(this, RetrofitActivityPokemonDetail::class.java).also {
            it.putExtra("pokemon", itemClicked)
        })
    }
}