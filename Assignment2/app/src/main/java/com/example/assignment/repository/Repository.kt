package com.example.assignment.repository

import android.content.Context
import androidx.room.Room

object Repository {
        private lateinit var db : EmployeDataBase
        fun getDatabase(context: Context): EmployeDataBase {
                db = Room.databaseBuilder(
                    context, EmployeDataBase::class.java, "employee_database"
                ).build()
            return db
        }
}