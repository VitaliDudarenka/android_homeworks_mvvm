package com.gmail.vitaliklancer.data.net

import com.gmail.vitaliklancer.data.entity.StudentDeleteRequest
import com.gmail.vitaliklancer.data.entity.StudentRequest
import com.gmail.vitaliklancer.data.entity.StudentResponse
import io.reactivex.Observable
import retrofit2.http.*

interface RestApi {
    @GET("students")
    fun getStudents(): Observable<List<StudentResponse>>

    //https://sdfvfs.by/api?students?id=gddg
    @GET("students")
    fun getStudentById(@Query("id") id: String): Observable<StudentResponse>

    //https://sdfvfs.by/students/23454523/
    @GET("students/{id}")
    fun getStudentById2(@Path("id") id: String): Observable<StudentResponse>

    //https://sdfvfs.by/students/
    @POST("students")
    fun updateStudent(@Body student: StudentRequest): Observable<StudentResponse>

    //https://sdfvfs.by/students/2345234/
    @PUT("students/{id}")
    fun updateStudent(@Path("id") id: String, @Body student: StudentRequest): Observable<StudentResponse>

    @POST("students")
    fun addStudent(@Body student: StudentRequest): Observable<StudentResponse>

    //https://sdfvfs.by/students/2345234/
    @DELETE("students/{id}")
    fun deleteStudent(@Path("id") id: String): Observable<String>

    //https://sdfvfs.by/students
    @DELETE("students")
    //@Header("key: value")
    fun deleteStudent(@Body student: StudentDeleteRequest): Observable<Void>
}