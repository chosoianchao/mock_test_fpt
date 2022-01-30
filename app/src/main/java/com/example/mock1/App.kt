package com.example.mock1

import android.app.Application
import androidx.room.Room
import com.example.mock1.databases.AppDB

class App : Application() {
    var db: AppDB? = null
        private set


    override fun onCreate() {
        super.onCreate()
        instance = this
        configDB()
    }

    private fun configDB() {
        db = Room.databaseBuilder(this, AppDB::class.java, "items-db").build()
    }

    companion object {
        var instance: App? = null
            private set
    }
}
