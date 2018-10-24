package com.gmail.vitaliklancer.domain.usecases

import com.gmail.vitaliklancer.domain.entity.Student
import com.gmail.vitaliklancer.domain.executor.PostExecutorThread
import com.gmail.vitaliklancer.domain.repositories.StudentRepository
import io.reactivex.Observable

class AddStudentUseCase(postExecutorThread: PostExecutorThread,
                           private val studentRepository: StudentRepository) : BaseUseCase(postExecutorThread) {
    fun add(student: Student): Observable<Student> {
        return studentRepository.add(student).observeOn(postExecutorThread).subscribeOn(workExecutorThread)
    }
}