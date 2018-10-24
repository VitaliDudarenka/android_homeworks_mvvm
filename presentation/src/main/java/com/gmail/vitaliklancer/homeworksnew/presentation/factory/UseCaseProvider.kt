package com.gmail.vitaliklancer.homeworksnew.presentation.factory

import com.gmail.vitaliklancer.data.db.AppDataBase
import com.gmail.vitaliklancer.data.net.RestService
import com.gmail.vitaliklancer.data.repositories.StudentRepositoryImp
import com.gmail.vitaliklancer.domain.usecases.*
import com.gmail.vitaliklancer.homeworksnew.presentation.app.App
import com.gmail.vitaliklancer.homeworksnew.presentation.executor.UIThread

object UseCaseProvider {
    val uiThread = UIThread()
    val restService = RestService("https://api.backendless.com/B179074D-73A0-2E47-FF4B-F8A3D6D5C900/0484F0E5-86A0-FFEA-FF97-E0517E6D2100/data/")
    fun provideStudentListUseCase(): GetStudentUseCase {
        val studentDao = AppDataBase.getInstance(App.instance.applicationContext).getStudentDao()
        val repository = StudentRepositoryImp(restService, studentDao)
        return GetStudentUseCase(uiThread, repository)
    }

    fun provideStudentUseCase(): GetByIdUseCase {
        val studentDao = AppDataBase.getInstance(App.instance.applicationContext).getStudentDao()
        return GetByIdUseCase(uiThread, StudentRepositoryImp(restService, studentDao))
    }

    fun provideSearchStudentUseCase(): SearchStudentUseCase {
        val studentDao = AppDataBase.getInstance(App.instance.applicationContext).getStudentDao()
        return SearchStudentUseCase(uiThread, StudentRepositoryImp(restService, studentDao))
    }

    fun provideUpdateStudentUseCase(): UpdateStudentUseCase {
        val studentDao = AppDataBase.getInstance(App.instance.applicationContext).getStudentDao()
        return UpdateStudentUseCase(uiThread, StudentRepositoryImp(restService, studentDao))
    }

    fun provideAddStudentUseCase(): AddStudentUseCase {
        val studentDao = AppDataBase.getInstance(App.instance.applicationContext).getStudentDao()
        return AddStudentUseCase(uiThread, StudentRepositoryImp(restService, studentDao))
    }


    fun provideDeleteStudentUseCase(): DeleteStudentUseCase {
        val studentDao = AppDataBase.getInstance(App.instance.applicationContext).getStudentDao()
        return DeleteStudentUseCase(uiThread, StudentRepositoryImp(restService, studentDao))
    }
}