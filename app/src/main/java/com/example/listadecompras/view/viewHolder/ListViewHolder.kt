package com.example.listadecompras.view.viewHolder;

import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.listadecompras.databinding.RowItemsListBinding;
import com.example.listadecompras.model.ItemModel

class ListViewHolder(private val bind: RowItemsListBinding, val onEditClick: (Int) -> Unit, val onRemoveClick: (Int) -> Unit) : RecyclerView.ViewHolder(bind.root){
    //HOLDS THE VIEW OF OUR RECYCLERVIEW

    fun bind(shoppingList: ItemModel) {
        bind.rowItemName.text = "Item: ${shoppingList.itemName}"
        bind.rowItemQuantity.text = "Quantidade: ${shoppingList.itemQuantity.toString()}"


        bind.btnEdit.setOnClickListener {
            onEditClick(shoppingList.id)
        }

        bind.btnRemove.setOnClickListener{
            AlertDialog.Builder(itemView.context)
                .setTitle("Remoção de convidado")
                .setMessage("Tem certeza que deseja remover?")
                .setPositiveButton("Sim") { dialog, which ->
                    onRemoveClick(shoppingList.id)
                }
                .setNegativeButton("Não", null)
                .create()
                .show()
        }

    }
}