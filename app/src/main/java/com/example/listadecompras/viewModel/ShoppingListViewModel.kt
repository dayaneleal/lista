package com.example.listadecompras.viewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.listadecompras.model.ShoppingListModel
import com.example.listadecompras.repository.ShoppingListRepository

class ShoppingListViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ShoppingListRepository.getInstance(application)

    private val listAllItems = MutableLiveData<List<ShoppingListModel>>()
    val items: LiveData<List<ShoppingListModel>> = listAllItems

    fun insert(item: ShoppingListModel){
        val success = repository.insert(item)

        if (success) {
            Toast.makeText(getApplication(),
                "Item salvo com sucesso.",
                Toast.LENGTH_LONG)
                .show();
        }
    }

    fun get(id : Int) {
        repository.get(id)
    }

    fun getAll() {
        listAllItems.value = repository.getAll()
    }
}