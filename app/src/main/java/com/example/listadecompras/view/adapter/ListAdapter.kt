package com.example.listadecompras.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listadecompras.databinding.RowItemsListBinding
import com.example.listadecompras.model.ShoppingListModel
import com.example.listadecompras.view.viewHolder.ListViewHolder

class ListAdapter: RecyclerView.Adapter<ListViewHolder>() {
    private var shoppingList: List<ShoppingListModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val item = RowItemsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(item)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(shoppingList[position])
    }

    override fun getItemCount(): Int {
        return shoppingList.count()
    }

    fun updateList(list: List<ShoppingListModel>){
        shoppingList = list
        notifyDataSetChanged()
    }

}