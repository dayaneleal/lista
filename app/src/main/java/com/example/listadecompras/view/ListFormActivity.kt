package com.example.listadecompras.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.listadecompras.R
import com.example.listadecompras.constants.DBConstants
import com.example.listadecompras.databinding.ActivityNewItemsBinding
import com.example.listadecompras.model.ItemModel
import com.example.listadecompras.viewModel.ListViewModel

class ListFormActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityNewItemsBinding
    private lateinit var  viewModel: ListViewModel

    private var itemId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)

        //Calling Action Bar
        var actionBar = getSupportActionBar()

        //showing the back button in action bar
        if (actionBar != null){
            actionBar.setDisplayShowTitleEnabled(false)
            actionBar.setDisplayHomeAsUpEnabled(true)
        }

        binding.btnSave.setOnClickListener(this)

        observe()

        loadData()
    }

    override fun onClick(v: View) {
        if(v.id == R.id.btn_save){
            val name = binding.edtNameItem.text.toString()
            val quantity = binding.edtQuantity.text.toString().toInt()
            val id = itemId

            val model = ItemModel(id, name, quantity)
            viewModel.save(model)

            finish()
        }
    }

    private fun loadData() {
        val bundle = intent.extras

        if (bundle != null) {
            itemId = bundle.getInt(DBConstants.SHOPPING_LIST.ID)
            viewModel.get(itemId)
        }
    }

    private fun observe() {
        viewModel.itemLiveData.observe(this){
            binding.edtNameItem.setText(it.itemName)
            binding.edtQuantity.setText(it.itemQuantity.toString())
        }

    }
}