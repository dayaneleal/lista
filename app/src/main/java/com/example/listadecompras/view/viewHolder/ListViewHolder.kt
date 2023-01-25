package com.example.listadecompras.view.viewHolder;

import androidx.recyclerview.widget.RecyclerView
import com.example.listadecompras.databinding.RowItemsListBinding;
import com.example.listadecompras.model.ItemModel

class ListViewHolder(private val bind: RowItemsListBinding, val onEditClick: (Int) -> Unit) : RecyclerView.ViewHolder(bind.root){
    //HOLDS THE VIEW OF OUR RECYCLERVIEW

    fun bind(shoppingList: ItemModel) {
        bind.rowItemName.text = "Item: ${shoppingList.itemName}"
        bind.rowItemQuantity.text = "Quantidade: ${shoppingList.itemQuantity.toString()}"


        bind.rowItemName.setOnClickListener {
            onEditClick(shoppingList.id)
        }

    }
}