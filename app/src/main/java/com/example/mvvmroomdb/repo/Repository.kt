package com.example.mvvmroomdb.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmroomdb.database.DataDao
import com.example.mvvmroomdb.database.DataItem
import com.example.mvvmroomdb.retrofit.RemoteApi
import com.example.mvvmroomdb.retrofit.model.ResponceItem
import com.example.mvvmroomdb.utils.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(private val dataDao : DataDao) {

    private val api : RemoteApi = NetworkUtils.getRetrofitInstance().create(RemoteApi::class.java)

    val allData : LiveData<List<DataItem>> = dataDao.readAllData()

    suspend fun addData(data: DataItem){
        dataDao.addData(data)
    }

    suspend fun deleteData(data: DataItem){
        dataDao.deleteData(data)
    }


    private val _remoteData : MutableLiveData<ResponceItem> = MutableLiveData()
    val remoteData : LiveData<ResponceItem> = _remoteData

    fun getRemoteData() {
        api.getUsers(2).enqueue(object : Callback<ResponceItem>{
            override fun onResponse(call: Call<ResponceItem>, response: Response<ResponceItem>) {
                if (response.isSuccessful){
                    _remoteData.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<ResponceItem>, t: Throwable) {
                Log.d("API_Response", "Failed to download data")
            }

        })
    }
}