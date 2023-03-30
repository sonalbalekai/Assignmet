package com.example.assignment.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment.data.Data
import com.example.assignment.databinding.ActivityMainBinding
import com.example.assignment.viewmodel.DataViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: DataViewModel
    private lateinit var list: MutableList<Data>
    private lateinit var orderTypeAdapter: EmployeeRecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Setup binding the View UI
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        list = arrayListOf()
        viewModel = ViewModelProvider(this).get(DataViewModel::class.java)
        orderTypeAdapter = EmployeeRecyclerView(list)
        // this creates a vertical layout Manager
        binding.sampleRecycler.layoutManager = LinearLayoutManager(this)
        binding.sampleRecycler.setHasFixedSize(true)
        binding.sampleRecycler.adapter = orderTypeAdapter
        viewModel.makeRequest()
        observeData()
    }

    private fun observeData() {
        viewModel.getAllData(application).observe(this) {
            if (it != null) {
                orderTypeAdapter.updateList(it)
            }
        }
    }
}