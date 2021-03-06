package com.example.posttest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_users.view.*

class RVusers(var allTheUsers: ArrayList<String>): RecyclerView.Adapter<RVusers.ItemViewHolder>() {
    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_users, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val aNew = allTheUsers[position]
        holder.itemView.apply{
            tvUsers.text = aNew
        }
    }

    override fun getItemCount()= allTheUsers.size
}