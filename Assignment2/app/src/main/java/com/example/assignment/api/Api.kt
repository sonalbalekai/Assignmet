package com.example.assignment.api

import com.example.assignment.data.Model
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

public interface Api {
    @GET("users")
    fun getData(@Query("page") page : Int,@Query("per_page") per_page: Int): Call<Model>
}