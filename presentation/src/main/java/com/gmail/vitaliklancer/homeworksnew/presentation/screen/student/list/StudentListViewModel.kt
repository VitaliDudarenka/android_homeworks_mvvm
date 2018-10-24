package com.gmail.vitaliklancer.homeworksnew.presentation.screen.student.list

import android.databinding.ObservableBoolean
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.gmail.vitaliklancer.domain.entity.Student
import com.gmail.vitaliklancer.domain.entity.StudentSearch
import com.gmail.vitaliklancer.homeworksnew.presentation.base.BaseViewModel
import com.gmail.vitaliklancer.homeworksnew.presentation.factory.UseCaseProvider
import com.gmail.vitaliklancer.homeworksnew.presentation.screen.student.StudentRouter
import io.reactivex.rxkotlin.subscribeBy

class StudentListViewModel : BaseViewModel<StudentRouter>() {
    var adapter: StudentListAdapter? = StudentListAdapter()
    val isProgressEnabled = ObservableBoolean(false)
    private val studentListUseCase = UseCaseProvider.provideStudentListUseCase()
    private val searchStudentUseCase = UseCaseProvider.provideSearchStudentUseCase()

    init {
        isProgressEnabled.set(true)
        val disposable = studentListUseCase.get().subscribeBy(
                onNext = {
                    //adapter = StudentListAdapter()
                    adapter!!.listData = it.toMutableList()
                    isProgressEnabled.set(false)
                },
                onError = {
                    isProgressEnabled.set(false)
                    router?.showError(it)
                }
        )
        addToDisposable(disposable)
        adapter!!.onItemClickListener = object : StudentListAdapter.OnItemClickListener {
            override fun onItemClick(student: Student) {
                Log.e("AAA", student.id)
                router!!.goToStudentDetails(student.id)
            }
        }
    }

    fun search(search: String) {
        if (isProgressEnabled.get()) return
        val studentSearch = StudentSearch(search)
        val disposable = searchStudentUseCase.search(studentSearch).subscribeBy(
                onNext = {
                    adapter!!.filter(search)
                },
                onError = {
                    router?.showError(it)
                }
        )
        addToDisposable(disposable)
    }

    fun onClickAdd() {
        router!!.goToStudentAdd()
    }
}