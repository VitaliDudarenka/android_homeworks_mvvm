package com.gmail.vitaliklancer.data.db.dao

import android.arch.persistence.room.*
import com.gmail.vitaliklancer.data.db.entity.StudentDB
import io.reactivex.Flowable
import io.reactivex.Observable


@Dao
interface StudentDao {
    @Insert
    fun insert(student: List<StudentDB>)

    @Update
    fun update(student: StudentDB)

    @Delete
    fun delete(student: StudentDB)

    @Query("DELETE FROM student")
    fun deleteAll()

    @Query("SELECT * FROM student ORDER BY name")
    fun getAll(): Flowable<List<StudentDB>>

    @Query("SELECT * FROM student WHERE id = :id LIMIT 1")
    fun getById(id: String): Flowable<StudentDB>

    @Query("DELETE FROM student WHERE id = :id")
    fun deleteById(id: String)
}