package com.cmm.kotlidroid.retrofit.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.cmm.kotlidroid.retrofit.model.Pokemon

@Dao
interface PokemonDao {

    @Query("Select * From pokemons Order By pokemonId ASC")
    fun selectAll(): LiveData<List<Pokemon>>

    @Insert
    fun insert(pokemon: Pokemon)

    @Delete
    fun delete(pokemon: Pokemon)

    @Query("Delete From pokemons")
    fun deleteAll()
}