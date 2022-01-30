package com.example.mock1.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mock1.OnActionCallBack
import com.example.mock1.R
import com.example.mock1.databases.entities.Items

class ItemsAdapter(
    private var items: List<Items>,
    private val context: Context?,
    private val callBack: OnActionCallBack,
) :
    RecyclerView.Adapter<ItemsAdapter.Companion.ItemsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = layoutInflater.inflate(R.layout.item_list_data, parent, false)
        return ItemsViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(mItems: List<Items>) {
        items = mItems
        notifyDataSetChanged()
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        val mItems: Items = items[position]
        holder.name.text = "Name: " + mItems.name
        holder.type.text = "Type: " + mItems.type
        holder.money.text = "Money: " + mItems.money.toString()
        holder.itemView.setOnClickListener {
            it.startAnimation(
                AnimationUtils.loadAnimation(
                    context,
                    androidx.appcompat.R.anim.abc_popup_enter
                )
            )
            callBack.callBack(mItems, "DATA")
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    companion object {
        class ItemsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val name: TextView = itemView.findViewById(R.id.tv_name)
            val type: TextView = itemView.findViewById(R.id.tv_type)
            val money: TextView = itemView.findViewById(R.id.tv_money)
        }
    }
}
