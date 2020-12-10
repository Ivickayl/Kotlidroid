package com.cmm.kotlidroid.architecture

import android.app.Application
import androidx.room.Room

class CustomApplication : Application() {

    companion object {
        lateinit var instance: CustomApplication
    }

    val applicationDatabase: CustomDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            CustomDatabase::class.java,
            "MyBeautifulDatabase"
        ).fallbackToDestructiveMigration().build()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}