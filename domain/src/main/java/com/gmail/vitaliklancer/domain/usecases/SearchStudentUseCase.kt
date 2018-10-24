package com.gmail.vitaliklancer.domain.usecases

import com.gmail.vitaliklancer.domain.entity.Student
import com.gmail.vitaliklancer.domain.entity.StudentSearch
import com.gmail.vitaliklancer.domain.executor.PostExecutorThread
import com.gmail.vitaliklancer.domain.repositories.StudentRepository
import io.reactivex.Observable

class SearchStudentUseCase(postExecutorThread: PostExecutorThread,
                           private val studentRepository: StudentRepository) : BaseUseCase(postExecutorThread) {
    fun search(searchStudent: StudentSearch): Observable<List<Student>> {
        return studentRepository.search(searchStudent).observeOn(postExecutorThread).subscribeOn(workExecutorThread)
    }


}