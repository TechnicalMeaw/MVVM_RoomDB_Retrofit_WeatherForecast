package com.example.mvvmroomdb.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DataItem::class], version = 1, exportSchema = false)
abstract class DataDatabase : RoomDatabase() {

    companion object{
        private var databaseInstance : DataDatabase? = null

        fun getDatabaseInstance(context: Context) : DataDatabase{
            if (databaseInstance != null){
                return databaseInstance as DataDatabase
            }else{
                databaseInstance = Room.databaseBuilder(context, DataDatabase::class.java, "data_database")
                    .build()

                return databaseInstance as DataDatabase
            }
        }
    }

    abstract fun getDataDao() : DataDao
}