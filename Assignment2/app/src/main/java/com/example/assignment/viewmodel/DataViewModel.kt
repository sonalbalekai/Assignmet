package com.example.assignment.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment.api.RetrofitHelper
import com.example.assignment.data.Data
import com.example.assignment.data.Model
import com.example.assignment.repository.DataDao
import com.example.assignment.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataViewModel : ViewModel() {
    private lateinit var employeeDao: DataDao
    private val _data = MutableLiveData<List<Data>>()
    val data: LiveData<List<Data>> get() = _data
    val TAG: String = DataViewModel::class.simpleName.toString()

    fun getAllData(applicationContext: Application): LiveData<List<Data>> {
        val db = Repository.getDatabase(applicationContext)
        employeeDao = db.dataDao()
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _data.postValue(employeeDao.getAllEmployee())
            }
        }
        return data
    }

    fun makeRequest() {
        val call: Call<Model> = RetrofitHelper.getInstance().getData(1, 12)
        call.enqueue(object : Callback<Model> {
            override fun onResponse(call: Call<Model>, response: Response<Model>) {
                if (response.isSuccessful) {
                    viewModelScope.launch {
                        withContext(Dispatchers.IO) {
                            employeeDao.deleteEmployee()
                            response.body()?.data?.let { employeeDao.insert(it.toList()) }
                            Log.e(TAG, response.body().toString())
                        }
                    }
                }
            }

            override fun onFailure(call: Call<Model>, t: Throwable) {
                Log.e(TAG, t.message.toString())
            }
        })
    }
}