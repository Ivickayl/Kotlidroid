package com.cmm.kotlidroid.architecture

import com.cmm.kotlidroid.retrofit.endpoint.PokemonEndpoint
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/").addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder()
                    .excludeFieldsWithoutExposeAnnotation()
                    .create()
            )
        ).build()

    fun getPokemon(): PokemonEndpoint = retrofit.create(PokemonEndpoint::class.java)

}