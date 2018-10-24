package com.gmail.vitaliklancer.domain.usecases

import com.gmail.vitaliklancer.domain.executor.PostExecutorThread
import com.gmail.vitaliklancer.domain.repositories.StudentRepository
import io.reactivex.Completable
import io.reactivex.Observable

class DeleteStudentUseCase(postExecutorThread: PostExecutorThread,
                           private val studentRepository: StudentRepository) : BaseUseCase(postExecutorThread) {
    fun delete(studentId: String): Observable<String> {
        return studentRepository.delete(studentId).observeOn(postExecutorThread).subscribeOn(workExecutorThread)
    }
}