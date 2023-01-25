package com.example.listadecompras.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listadecompras.databinding.RowItemsListBinding
import com.example.listadecompras.model.ItemModel
import com.example.listadecompras.view.viewHolder.ListViewHolder

class ListAdapter(val onEditClick: (Int) -> Unit): RecyclerView.Adapter<ListViewHolder>() {
    private var shoppingList: List<ItemModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val item = RowItemsListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return ListViewHolder(item, onEditClick)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(shoppingList[position])
    }

    override fun getItemCount(): Int {
        return shoppingList.count()
    }

    fun updateList(list: List<ItemModel>){
        shoppingList = list
        notifyDataSetChanged()
    }



}