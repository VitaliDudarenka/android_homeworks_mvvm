package com.gmail.vitaliklancer.homeworksnew.presentation.screen.student.list

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.gmail.vitaliklancer.homeworksnew.R
import com.gmail.vitaliklancer.homeworksnew.databinding.FragmentStudentListBinding
import com.gmail.vitaliklancer.homeworksnew.presentation.base.BaseMvvmFragment
import com.gmail.vitaliklancer.homeworksnew.presentation.screen.student.StudentRouter
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.rxkotlin.subscribeBy
import java.util.concurrent.TimeUnit


class StudentListFragment : BaseMvvmFragment<StudentListViewModel, StudentRouter, FragmentStudentListBinding>() {
    companion object {
        fun getInstance(): StudentListFragment {
            return StudentListFragment()
        }
    }

    override fun provideViewModel(): StudentListViewModel {
        return ViewModelProviders.of(this).get(StudentListViewModel::class.java)
    }

    override fun provideLayoutId(): Int = R.layout.fragment_student_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.listRecyclerView.adapter = viewModel.adapter
        binding.listRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.listRecyclerView.setHasFixedSize(true)

        RxTextView.textChanges(binding.searchEditText)
                //.throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribeBy {
            viewModel.search(it.toString())
        }


    }

}