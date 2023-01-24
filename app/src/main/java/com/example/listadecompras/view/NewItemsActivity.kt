package com.example.listadecompras.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.ViewModelProvider
import com.example.listadecompras.R
import com.example.listadecompras.databinding.ActivityNewItemsBinding
import com.example.listadecompras.model.ShoppingListModel
import com.example.listadecompras.viewModel.ShoppingListViewModel

class NewItemsActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityNewItemsBinding
    private lateinit var  viewModel: ShoppingListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(ShoppingListViewModel::class.java)

        //Calling Action Bar
        var actionBar = getSupportActionBar()

        //showing the back button in action bar
        if (actionBar != null){
            actionBar.setDisplayShowTitleEnabled(false)
            actionBar.setDisplayHomeAsUpEnabled(true)
        }

        binding.btnSave.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if(v.id == R.id.btn_save){
            val name = binding.edtNameItem.text.toString()
            val quantity = binding.edtQuantity.text.toString().toInt()

            val model = ShoppingListModel(0, name, quantity)
            viewModel.insert(model)

        }
    }
}