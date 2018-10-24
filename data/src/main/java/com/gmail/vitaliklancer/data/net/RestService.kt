package com.gmail.vitaliklancer.data.net

import android.util.Log
import com.gmail.vitaliklancer.data.entity.StudentRequest
import com.gmail.vitaliklancer.data.entity.StudentResponse
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RestService(private val apiUrl: String) {
    private val restApi: RestApi

    init {
        val okHttpBuilder = OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
        okHttpBuilder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        val gson = GsonBuilder()
                .create()
        val retrofit = Retrofit.Builder()
                .baseUrl(apiUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpBuilder.build())
                .build()
        restApi = retrofit.create(RestApi::class.java)
    }

    fun getStudents(): Observable<List<StudentResponse>> {
        return restApi.getStudents()
    }

    fun getStudentById(id: String): Observable<StudentResponse> {
        return restApi.getStudentById2(id)
    }

    fun updateStudent(student: StudentRequest): Observable<StudentResponse> {
        return restApi.updateStudent(student.id, student)
    }

    fun addStudent(student: StudentRequest): Observable<StudentResponse> {
        Log.e("CCC", student.name)
        return restApi.addStudent(student)
    }

    fun deleteStudent(id: String): Observable<String> {
        return restApi.deleteStudent(id)
    }
}