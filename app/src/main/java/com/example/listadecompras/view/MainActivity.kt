package com.example.listadecompras.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listadecompras.databinding.ActivityMainBinding
import com.example.listadecompras.view.adapter.ListAdapter
import com.example.listadecompras.viewModel.ShoppingListViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val adapter = ListAdapter()
    private lateinit var viewModel: ShoppingListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(ShoppingListViewModel::class.java)

        binding.fab.setOnClickListener{
            it -> startActivity(Intent(applicationContext, NewItemsActivity::class.java))
        }

        //Layout
        binding.recylerItemList.layoutManager = LinearLayoutManager(this)

        //Adapter
        binding.recylerItemList.adapter = adapter

        viewModel.getAll()

        observe()
    }

    private fun observe() = viewModel.items.observe(
        this,
    ) {
        adapter.updateList(it)
    }
}