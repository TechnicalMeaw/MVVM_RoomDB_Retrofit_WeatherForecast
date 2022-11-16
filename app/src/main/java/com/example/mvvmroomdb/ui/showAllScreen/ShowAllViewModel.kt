package com.example.mvvmroomdb.ui.showAllScreen

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmroomdb.database.DataDatabase
import com.example.mvvmroomdb.database.DataItem
import com.example.mvvmroomdb.repo.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShowAllViewModel: ViewModel() {
    private lateinit var repository: Repository

    fun getAllData(context: Context): LiveData<List<DataItem>> {
        val dao = DataDatabase.getDatabaseInstance(context).getDataDao()
        repository = Repository(dao)
        return repository.allData
    }

    fun deleteData(context: Context, dataItem: DataItem){
        val dao = DataDatabase.getDatabaseInstance(context).getDataDao()
        repository = Repository(dao)
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteData(dataItem)
        }
    }
}