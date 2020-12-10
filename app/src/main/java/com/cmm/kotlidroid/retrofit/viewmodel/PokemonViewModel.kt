package com.cmm.kotlidroid.retrofit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmm.kotlidroid.retrofit.model.Pokemon
import com.cmm.kotlidroid.retrofit.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonViewModel : ViewModel() {

    private val pokemonRepository: PokemonRepository by lazy { PokemonRepository() }

    val pokedexMutableLiveData = pokemonRepository.pokedexMutableLiveData

    fun fetchPokemon(pokemonName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            pokemonRepository.fetchPokemon(pokemonName)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            pokemonRepository.deleteAll()
        }
    }

    fun delete(pokemonToBeDeleted: Pokemon) {
        viewModelScope.launch(Dispatchers.IO) {
            pokemonRepository.delete(pokemonToBeDeleted)
        }
    }
}