package com.example.listadecompras.view.viewHolder;

import androidx.recyclerview.widget.RecyclerView
import com.example.listadecompras.databinding.RowItemsListBinding;
import com.example.listadecompras.model.ShoppingListModel

class ListViewHolder(private val bind: RowItemsListBinding) : RecyclerView.ViewHolder(bind.root){
    //HOLDS THE VIEW OF OUR RECYCLERVIEW

    fun bind(shoppingList: ShoppingListModel) {
        bind.rowItemName.text = "Item: ${shoppingList.itemName}"
        bind.rowItemQuantity.text = "Quantidade: ${shoppingList.itemQuantity.toString()}"
    }
}