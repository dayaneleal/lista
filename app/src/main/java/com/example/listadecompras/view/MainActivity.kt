package com.example.listadecompras.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listadecompras.constants.DBConstants
import com.example.listadecompras.databinding.ActivityMainBinding
import com.example.listadecompras.view.adapter.ListAdapter
import com.example.listadecompras.viewModel.ListViewModel

class MainActivity : AppCompatActivity()  {

    private lateinit var binding : ActivityMainBinding
    private val adapter = ListAdapter(::onEditClick, ::onEditRemove)
    private lateinit var viewModel: ListViewModel
    private var itemId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)

        binding.fab.setOnClickListener{
            it -> startActivity(Intent(applicationContext, ListFormActivity::class.java))
        }

        //Layout
        binding.recylerItemList.layoutManager = LinearLayoutManager(this)

        //Adapter
        binding.recylerItemList.adapter = adapter

        viewModel.getAll()

        observe()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAll()
    }

    private fun observe() = viewModel.listItemLiveData.observe(
        this,
    ) {
        adapter.updateList(it)
    }

    fun onEditClick(id: Int) {
        Toast.makeText(applicationContext, "Fui clicado - ID: ${id}", Toast.LENGTH_SHORT).show()
        val intent = Intent(applicationContext, ListFormActivity::class.java)

        val bundle = Bundle()
        bundle.putInt(DBConstants.SHOPPING_LIST.ID, id)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    fun onEditRemove(id: Int) {
        viewModel.remove(id)
        viewModel.getAll()
    }



}