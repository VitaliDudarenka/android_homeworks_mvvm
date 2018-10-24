package com.gmail.vitaliklancer.domain.repositories

import com.gmail.vitaliklancer.domain.entity.Student
import com.gmail.vitaliklancer.domain.entity.StudentSearch
import io.reactivex.Completable
import io.reactivex.Observable

interface StudentRepository : BaseRepository {
    fun get(): Observable<List<Student>>
    fun search(search: StudentSearch): Observable<List<Student>>
    fun update(student: Student): Observable<Student>
    fun add(student: Student): Observable<Student>
    fun delete(studentId: String): Observable<String>
    fun getById(id: String): Observable<Student>
}