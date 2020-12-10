package com.cmm.kotlidroid.retrofit.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "pokemons")
data class Pokemon(

    @Expose
    @SerializedName("name")
    val name: String,

    @Expose
    @SerializedName("id")
    val pokemonId: String,

    @Expose
    @SerializedName("weight")
    val weight: Double,

    @Expose
    @SerializedName("sprites")
    @Embedded
    val sprites: Sprite,

    @Expose
    @SerializedName("types")
    val types: List<TypeSlot>,

    @Expose
    @SerializedName("stats")
    val stats: List<StatSlot>
) : Serializable {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    override fun toString(): String {
        return "My pokemon name is : ${this.name}"
    }
}