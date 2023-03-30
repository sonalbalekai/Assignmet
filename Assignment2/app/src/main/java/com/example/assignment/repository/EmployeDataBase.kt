package com.example.assignment.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.assignment.data.Data

@Database(entities = [Data::class], version = 5)
abstract class EmployeDataBase : RoomDatabase() {
    abstract fun dataDao(): DataDao
}