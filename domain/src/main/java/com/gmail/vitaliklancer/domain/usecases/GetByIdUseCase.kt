package com.gmail.vitaliklancer.domain.usecases

import com.gmail.vitaliklancer.domain.entity.Student
import com.gmail.vitaliklancer.domain.executor.PostExecutorThread
import com.gmail.vitaliklancer.domain.repositories.StudentRepository
import io.reactivex.Observable

class GetByIdUseCase(postExecutorThread: PostExecutorThread,
                     private val studentRepository: StudentRepository) : BaseUseCase(postExecutorThread) {
    fun getById(id: String): Observable<Student> {
        return studentRepository.getById(id).observeOn(postExecutorThread).subscribeOn(workExecutorThread)
    }
}