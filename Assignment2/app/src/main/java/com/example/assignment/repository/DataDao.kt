package com.example.assignment.repository

import androidx.room.*
import com.example.assignment.data.Data

@Dao
interface DataDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(data: List<Data>)

    @Query("SELECT * FROM emplyeeTable")
    fun getAllEmployee(): List<Data>

    @Query("Delete FROM emplyeeTable")
    fun deleteEmployee()
}