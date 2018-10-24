package com.gmail.vitaliklancer.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.gmail.vitaliklancer.data.db.dao.StudentDao
import com.gmail.vitaliklancer.data.db.entity.StudentDB


@Database(entities = [StudentDB::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "StudentLocalBase"
        fun getInstance(context: Context): AppDataBase {
            return Room.databaseBuilder(context, AppDataBase::class.java, DATABASE_NAME).fallbackToDestructiveMigration().build()
        }
    }

    abstract fun getStudentDao(): StudentDao

}
