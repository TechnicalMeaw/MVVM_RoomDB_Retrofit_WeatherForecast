package com.example.mvvmroomdb.retrofit

import com.example.mvvmroomdb.retrofit.model.ResponceItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface RemoteApi {

    @GET("api/users")
    fun getUsers(@Query("page") pageNumber : Int) : Call<ResponceItem>
}