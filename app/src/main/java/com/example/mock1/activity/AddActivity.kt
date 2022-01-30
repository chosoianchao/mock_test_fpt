package com.example.mock1.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mock1.ItemsManager
import com.example.mock1.OnActionCallBack
import com.example.mock1.databases.entities.Items
import com.example.mock1.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity(), OnActionCallBack {
    private lateinit var viewBinding: ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initViews()
    }

    private fun initViews() {
        viewBinding.btAdd.setOnClickListener {
            if (viewBinding.edtName.text.isEmpty() && viewBinding.edtType.text.isEmpty() && viewBinding.edtMoney.text.isEmpty()) {
                Toast.makeText(this, "Please insert value first", Toast.LENGTH_SHORT).show()
            } else {
                val money = viewBinding.edtMoney.text.toString()
                val a = money.toInt()
                val items = Items(null,
                    viewBinding.edtName.text.toString().trim(),
                    viewBinding.edtType.text.toString().trim(),
                    a)
                ItemsManager.itemsManager?.insertData(items, this)
            }
        }
    }

    override fun callBack(data: Any?, key: String?) {
        if (key.equals("insert")) {
            hideKeyboard(activity = this)
            onBackPressed()
        }
    }
}


