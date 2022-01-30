package com.example.mock1.activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mock1.ItemsManager
import com.example.mock1.OnActionCallBack
import com.example.mock1.R
import com.example.mock1.adapter.ItemsAdapter
import com.example.mock1.databases.entities.Items
import com.example.mock1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnActionCallBack {
    private lateinit var viewBinding: ActivityMainBinding
    private var listItems: ArrayList<Items> = ArrayList()
    private var listItemsName: ArrayList<Items> = ArrayList()
    private var listItemsMoney: ArrayList<Items> = ArrayList()
    private var listItemsSearchName: ArrayList<Items> = ArrayList()
    private var listItemsSearchMoney: ArrayList<Items> = ArrayList()
    private var adapter: ItemsAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initViews()
    }

    private fun initViews() {
        viewBinding.rvItems.layoutManager = LinearLayoutManager(this)
        viewBinding.rvItems.hasFixedSize()
        viewBinding.rvItems.addItemDecoration(DividerItemDecoration(this,
            LinearLayoutManager.VERTICAL))
        getAllItems()
        getAllItemsName()
        getAllItemsMoney()
        viewBinding.btDisplay.setOnClickListener {
            showListItems()
        }
        viewBinding.btSortName.setOnClickListener {
            showListSortByName()
        }
        viewBinding.btSortMoney.setOnClickListener {
            showListSortByMoney()
        }
        viewBinding.btSearch.setOnClickListener {
            val search = viewBinding.edtSearch.text.toString()
            val number = search.toIntOrNull()
            val isInteger = number != null
            if (isInteger) {
                searchMoney(search)
            } else {
                searchName(search)
            }
            viewBinding.edtSearch.setText("")
            hideKeyboard(activity = this)
        }
        viewBinding.swRefresh.setOnRefreshListener {
            listItems.clear()
            listItemsName.clear()
            listItemsMoney.clear()
            recreate()
            viewBinding.swRefresh.isRefreshing = false
        }
    }

    private fun searchName(search: String) {
        listItemsSearchName.clear()
        ItemsManager.itemsManager?.searchName(search, listItemsSearchName)
        adapter = ItemsAdapter(listItemsSearchName, this, this)
        viewBinding.rvItems.adapter = adapter
        adapter?.setData(listItemsSearchName)
    }

    private fun searchMoney(search: String) {
        listItemsSearchMoney.clear()
        ItemsManager.itemsManager?.searchMoney(search.toInt(), listItemsSearchMoney)
        adapter = ItemsAdapter(listItemsSearchMoney, this, this)
        viewBinding.rvItems.adapter = adapter
        adapter?.setData(listItemsSearchMoney)
    }

    private fun getAllItemsMoney() {
        ItemsManager.itemsManager?.sortByMoney(listItemsMoney)
    }

    private fun getAllItemsName() {
        ItemsManager.itemsManager?.sortByName(listItemsName)
    }

    private fun showListSortByName() {
        adapter = ItemsAdapter(listItemsName, this, this)
        viewBinding.rvItems.adapter = adapter
        adapter?.setData(listItemsName)
    }

    private fun showListSortByMoney() {
        adapter = ItemsAdapter(listItemsMoney, this, this)
        viewBinding.rvItems.adapter = adapter
        adapter?.setData(listItemsMoney)
    }

    private fun showListItems() {
        adapter = ItemsAdapter(listItems, this, this)
        viewBinding.rvItems.adapter = adapter
        adapter?.setData(listItems)
    }

    private fun getAllItems() {
        ItemsManager.itemsManager?.getAllItems(listItems)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.add) {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    override fun callBack(data: Any?, key: String?) {
        if (key.equals("DATA")) {
            dialog(data)
        }
    }

    private fun dialog(data: Any?) {
        val option = arrayOf<CharSequence>("Update", "Delete", "Cancel")
        val items: Items = data as Items
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder.setItems(option, fun(dialog: DialogInterface, item: Int) {
            when {
                option[item] == "Update" -> {
                    val intent = Intent(this, UpdateActivity::class.java)
                    intent.putExtra("items", items)
                    startActivity(intent)
                }
                option[item] == "Delete" -> {
                    ItemsManager.itemsManager?.deleteData(items)
                }
                option[item] == "Cancel" -> {
                    dialog.dismiss()
                }
            }
        }).show()
    }
}