package com.cmm.kotlidroid.architecture

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cmm.kotlidroid.android.dao.LightStatusDao
import com.cmm.kotlidroid.android.model.LightStatusValue
import com.cmm.kotlidroid.retrofit.dao.PokemonDao
import com.cmm.kotlidroid.retrofit.model.Pokemon

@TypeConverters(Converters::class)
@Database(
    entities = [
        LightStatusValue::class,
        Pokemon::class
    ], version = 3, exportSchema = false
)
abstract class CustomDatabase : RoomDatabase() {
    abstract fun lightSensorValueDao(): LightStatusDao
    abstract fun pokemonDao(): PokemonDao
}