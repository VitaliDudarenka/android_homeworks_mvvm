package com.gmail.vitaliklancer.homeworksnew.presentation.screen.student.add

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.gmail.vitaliklancer.homeworksnew.R
import com.gmail.vitaliklancer.homeworksnew.databinding.FragmentStudentAddBinding
import com.gmail.vitaliklancer.homeworksnew.presentation.base.BaseMvvmFragment
import com.gmail.vitaliklancer.homeworksnew.presentation.screen.student.StudentRouter


class StudentAddFragment : BaseMvvmFragment<StudentAddViewModel, StudentRouter, FragmentStudentAddBinding>() {
    companion object {
        fun getInstance(): StudentAddFragment {
            return StudentAddFragment()
        }
    }
    override fun provideViewModel(): StudentAddViewModel {
        return ViewModelProviders.of(this).get(StudentAddViewModel::class.java)
    }

    override fun provideLayoutId(): Int = R.layout.fragment_student_add

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }



}