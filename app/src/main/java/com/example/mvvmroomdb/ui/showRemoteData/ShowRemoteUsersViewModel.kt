package com.example.mvvmroomdb.ui.showRemoteData

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmroomdb.database.DataDatabase
import com.example.mvvmroomdb.repo.Repository
import com.example.mvvmroomdb.retrofit.model.ResponceItem


class ShowRemoteUsersViewModel : ViewModel() {

    lateinit var remoteUsersData : LiveData<ResponceItem>

    fun getRemoteData(context: Context){
        val dao = DataDatabase.getDatabaseInstance(context).getDataDao()
        val repository = Repository(dao)

        repository.getRemoteData()
        remoteUsersData = repository.remoteData
    }

}