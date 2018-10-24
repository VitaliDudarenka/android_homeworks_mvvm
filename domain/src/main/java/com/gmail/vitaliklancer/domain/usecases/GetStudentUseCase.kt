package com.gmail.vitaliklancer.domain.usecases

import com.gmail.vitaliklancer.domain.entity.Student
import com.gmail.vitaliklancer.domain.executor.PostExecutorThread
import com.gmail.vitaliklancer.domain.repositories.StudentRepository
import io.reactivex.Observable

class GetStudentUseCase(postExecutorThread: PostExecutorThread,
                        private val studentRepository: StudentRepository) : BaseUseCase(postExecutorThread) {
    fun get(): Observable<List<Student>> {
        return studentRepository.get().observeOn(postExecutorThread).subscribeOn(workExecutorThread)
    }


}