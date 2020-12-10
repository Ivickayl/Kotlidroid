package com.cmm.kotlidroid.retrofit.repository

import androidx.lifecycle.LiveData
import com.cmm.kotlidroid.architecture.CustomApplication
import com.cmm.kotlidroid.architecture.RetrofitBuilder
import com.cmm.kotlidroid.retrofit.model.Pokemon
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class PokemonRepository {

    private var database: FirebaseDatabase =
        FirebaseDatabase.getInstance("https://kotlitroid-default-rtdb.europe-west1.firebasedatabase.app/")

    //private  var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private lateinit var myRef: DatabaseReference

    private val pokemonDao = CustomApplication.instance.applicationDatabase.pokemonDao()
    val pokedexMutableLiveData: LiveData<List<Pokemon>> = pokemonDao.selectAll()

    fun insert(pokemon: Pokemon) {
        pokemonDao.insert(pokemon)
    }

    fun deleteAll() {
        pokemonDao.deleteAll()
    }

    suspend fun fetchPokemon(pokemonName: String) {
        val pokemon: Pokemon = RetrofitBuilder.getPokemon().getAPokemonByName(pokemonName)

        insert(pokemon)
        myRef = database.getReference("pokemon/${pokemon.pokemonId}")
        myRef.setValue(pokemon)
    }

    fun delete(pokemonToBeDeleted: Pokemon) {
        pokemonDao.delete(pokemonToBeDeleted)
    }
}