package com.example.mock1.databases.dao

import androidx.room.*
import com.example.mock1.databases.entities.Items

@Dao
interface ItemsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(items: Items)

    @Query("SELECT * FROM Items")
    suspend fun getAll(): List<Items>

    @Query("SELECT * FROM Items WHERE Money > :x")
    suspend fun searchMoney(x: Int): List<Items>

    @Query("SELECT * FROM Items WHERE Name LIKE '%' || :search || '%'")
    suspend fun searchName(search: String): List<Items>

    @Query("SELECT * FROM Items ORDER BY Name ASC")
    suspend fun getAllByName(): List<Items>

    @Query("SELECT * FROM Items ORDER BY Money ASC")
    suspend fun getAllByMoney(): List<Items>

    @Query("UPDATE Items SET Name=:name,Type=:type,Money=:money WHERE ID=:id")
    suspend fun updateData(name: String, type: String, money: Int, id: Int)

    @Delete
    suspend fun deleteData(items: Items)
}