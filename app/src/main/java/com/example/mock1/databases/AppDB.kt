package com.example.mock1.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mock1.databases.dao.ItemsDao
import com.example.mock1.databases.entities.Items

@Database(entities = [Items::class], version = 1)
abstract class AppDB : RoomDatabase() {
    abstract val dao: ItemsDao?
}