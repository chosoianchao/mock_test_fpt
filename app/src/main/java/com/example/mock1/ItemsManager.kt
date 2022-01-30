package com.example.mock1

import android.widget.Toast
import com.example.mock1.databases.entities.Items
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ItemsManager {

    fun getAllItems(listData: ArrayList<Items>) {
        CoroutineScope(Dispatchers.IO).launch {
            val items: List<Items>? = App.instance?.db?.dao?.getAll()
            items?.let { listData.addAll(it) }
        }
    }

    fun sortByName(listData: ArrayList<Items>) {
        CoroutineScope(Dispatchers.IO).launch {
            val items = App.instance?.db?.dao?.getAllByName()
            items?.let { listData.addAll(it) }
        }
    }

    fun sortByMoney(listData: ArrayList<Items>) {
        CoroutineScope(Dispatchers.IO).launch {
            val items = App.instance?.db?.dao?.getAllByMoney()
            items?.let { listData.addAll(it) }
        }
    }

    fun insertData(items: Items, callBack: OnActionCallBack) {
        CoroutineScope(Dispatchers.IO).launch {
            App.instance?.db?.dao?.insertItem(items)
            withContext(Dispatchers.Main) {
                Toast.makeText(App.instance, "Insert success", Toast.LENGTH_SHORT).show()
                callBack.callBack(null, "insert")
            }
        }
    }

    fun searchName(search: String, listData: ArrayList<Items>) {
        CoroutineScope(Dispatchers.IO).launch {
            val items = App.instance?.db?.dao?.searchName(search)
            items?.let { listData.addAll(it) }
        }
    }

    fun searchMoney(x: Int, listData: ArrayList<Items>) {
        CoroutineScope(Dispatchers.IO).launch {
            val items = App.instance?.db?.dao?.searchMoney(x)
            items?.let { listData.addAll(it) }
        }
    }

    fun updateData(
        name: String,
        type: String,
        money: Int,
        iD: Int,
        callBack: OnActionCallBack,
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            App.instance?.db?.dao?.updateData(name, type, money, iD)
            withContext(Dispatchers.Main) {
                Toast.makeText(App.instance, "Update success", Toast.LENGTH_SHORT)
                    .show()
                callBack.callBack(null, "update")
            }
        }
    }

    fun deleteData(items: Items) {
        CoroutineScope(Dispatchers.IO).launch {
            App.instance?.db?.dao?.deleteData(items)
            withContext(Dispatchers.Main) {
                Toast.makeText(App.instance, "Delete success", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    companion object {
        @JvmStatic
        var itemsManager: ItemsManager? = null
            get() {
                if (field == null) {
                    field = ItemsManager()
                }
                return field
            }
            private set
    }
}