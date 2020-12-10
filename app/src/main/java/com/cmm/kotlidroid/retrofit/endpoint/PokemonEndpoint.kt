package com.cmm.kotlidroid.retrofit.endpoint

import com.cmm.kotlidroid.retrofit.model.Pokemon
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonEndpoint {

    @GET("pokemon/{name}")
    suspend fun getAPokemonByName(@Path("name") name: String): Pokemon

    @GET("pokemon/{id}")
    suspend fun getAPokemonById(@Path("id") name: Int): Pokemon
}