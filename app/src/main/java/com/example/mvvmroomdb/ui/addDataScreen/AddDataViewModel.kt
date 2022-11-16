package com.example.mvvmroomdb.ui.addDataScreen

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmroomdb.database.DataDatabase
import com.example.mvvmroomdb.database.DataItem
import com.example.mvvmroomdb.repo.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddDataViewModel : ViewModel() {

    private lateinit var repository: Repository

    fun addData(context: Context, dataItem: DataItem){
        val dao = DataDatabase.getDatabaseInstance(context).getDataDao()
        repository = Repository(dao)

        viewModelScope.launch(Dispatchers.IO){
            repository.addData(dataItem)
        }
    }
}