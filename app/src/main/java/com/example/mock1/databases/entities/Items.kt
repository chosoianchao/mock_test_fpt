package com.example.mock1.databases.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Items")
data class Items(
    @ColumnInfo(name = "ID")
    @PrimaryKey(autoGenerate = true)
    val iD: Int?,

    @ColumnInfo(name = "Name")
    val name: String? = null,

    @ColumnInfo(name = "Type")
    val type: String? = null,

    @ColumnInfo(name = "Money")
    val money: Int? = null,
) : Serializable {
    override fun toString(): String {
        return "Items(iD=$iD, name=$name, type=$type, money=$money)"
    }
}