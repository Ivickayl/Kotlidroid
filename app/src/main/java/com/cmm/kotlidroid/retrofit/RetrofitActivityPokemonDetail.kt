package com.cmm.kotlidroid.retrofit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cmm.kotlidroid.R
import com.cmm.kotlidroid.retrofit.model.Pokemon
import kotlinx.android.synthetic.main.activity_retrofit_pokemon_detail.*

class RetrofitActivityPokemonDetail : AppCompatActivity() {

    private lateinit var pokemon: Pokemon

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit_pokemon_detail)

        pokemon = intent.getSerializableExtra("pokemon") as Pokemon

        pokemon_details_name.setText(pokemon.name)
    }
}