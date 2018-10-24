package com.gmail.vitaliklancer.domain.usecases

import com.gmail.vitaliklancer.domain.entity.Student
import com.gmail.vitaliklancer.domain.executor.PostExecutorThread
import com.gmail.vitaliklancer.domain.repositories.StudentRepository
import io.reactivex.Completable
import io.reactivex.Observable

class UpdateStudentUseCase(postExecutorThread: PostExecutorThread,
                           private val studentRepository: StudentRepository) : BaseUseCase(postExecutorThread) {
    fun update(student: Student): Observable<Student> {
        return studentRepository.update(student).observeOn(postExecutorThread).subscribeOn(workExecutorThread)
    }
}