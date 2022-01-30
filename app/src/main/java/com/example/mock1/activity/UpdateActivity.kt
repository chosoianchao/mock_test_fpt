package com.example.mock1.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mock1.ItemsManager
import com.example.mock1.OnActionCallBack
import com.example.mock1.databases.entities.Items
import com.example.mock1.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity(), OnActionCallBack {
    private lateinit var viewBinding: ActivityUpdateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initViews()
    }

    private fun initViews() {
        val data = intent.getSerializableExtra("items")
        val items: Items = data as Items
        setText(items)
        viewBinding.btUpdate.setOnClickListener {
            val name = viewBinding.edtName.text.toString()
            val type = viewBinding.edtType.text.toString()
            val money = viewBinding.edtMoney.text.toString()
            ItemsManager.itemsManager?.updateData(name, type, money.toInt(), items.iD!!, this)
        }
    }

    private fun setText(items: Items) {
        viewBinding.edtName.setText(items.name)
        viewBinding.edtType.setText(items.type)
        viewBinding.edtMoney.setText(items.money.toString())
    }

    override fun callBack(data: Any?, key: String?) {
        if (key.equals("update")) {
            hideKeyboard(this)
            onBackPressed()
        }
    }
}