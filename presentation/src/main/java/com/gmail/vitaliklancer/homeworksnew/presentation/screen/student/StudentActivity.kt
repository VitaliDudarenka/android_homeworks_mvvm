package com.gmail.vitaliklancer.homeworksnew.presentation.screen.student

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.gmail.vitaliklancer.homeworksnew.R
import com.gmail.vitaliklancer.homeworksnew.presentation.base.BaseMvvmActivity
import android.databinding.DataBindingUtil
import com.gmail.vitaliklancer.homeworksnew.databinding.ActivityStudentBinding


class StudentActivity : BaseMvvmActivity<StudentViewModel, StudentRouter, ActivityStudentBinding>() {
    override fun provideRouter(): StudentRouter {
        return StudentRouter(this)
    }

    override fun provideViewModel(): StudentViewModel {
        return ViewModelProviders.of(this).get(StudentViewModel::class.java)
    }

    override fun provideLayoutId(): Int = R.layout.activity_student

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null)
            router.goToStudentList()
        val binding: ActivityStudentBinding = DataBindingUtil.setContentView(this, R.layout.activity_student)
        binding.viewModel = StudentViewModel()
    }


}