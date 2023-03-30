package com.example.assignment.viewmodel

import com.example.assignment.api.Api
import com.example.assignment.api.RetrofitHelper
import com.example.assignment.data.Model
import junit.framework.TestCase.assertTrue
import org.junit.Test
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

internal class DataViewModelTest {
    @Test
    fun makeRequest() {
        val api: Api =
            RetrofitHelper.getInstance()
        val call: Call<Model> = api.getData(1, 12)
        try {
            val response: Response<Model> = call.execute()
            assertTrue(response.isSuccessful)
        } catch (e: IOException) {
            e.printStackTrace();
        }
    }
}