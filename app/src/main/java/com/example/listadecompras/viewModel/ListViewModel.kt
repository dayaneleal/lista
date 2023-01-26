package com.example.listadecompras.viewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.listadecompras.model.ItemModel
import com.example.listadecompras.repository.ListRepository

class ListViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ListRepository.getInstance(application)

    private val _listItemLiveData = MutableLiveData<List<ItemModel>>()
    val listItemLiveData: LiveData<List<ItemModel>> = _listItemLiveData

    private val _itemLiveData = MutableLiveData<ItemModel>()
    val itemLiveData: LiveData<ItemModel> = _itemLiveData

    fun save(item: ItemModel){

        if (item.id == 0) {
            val success = repository.insert(item)

            if (success) {
                Toast.makeText(getApplication(),
                    "Item salvo com sucesso.",
                    Toast.LENGTH_LONG)
                    .show();
            }
        } else {
            val success = repository.update(item)

            if (success) {
                Toast.makeText(getApplication(),
                    "Item atualizado com sucesso.",
                    Toast.LENGTH_LONG)
                    .show();
            }
        }

    }

    fun get(id : Int) {
        _itemLiveData.value = repository.get(id)
    }

    fun getAll() {
        _listItemLiveData.value = repository.getAll()
    }

    fun remove(id: Int){
        repository.remove(id)
    }
}