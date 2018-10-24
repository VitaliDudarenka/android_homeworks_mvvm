package com.gmail.vitaliklancer.homeworksnew.presentation.screen.student

import android.view.View
import com.gmail.vitaliklancer.homeworksnew.R
import com.gmail.vitaliklancer.homeworksnew.presentation.base.BaseRouter
import com.gmail.vitaliklancer.homeworksnew.presentation.screen.student.add.StudentAddFragment
import com.gmail.vitaliklancer.homeworksnew.presentation.screen.student.details.StudentDetailsFragment
import com.gmail.vitaliklancer.homeworksnew.presentation.screen.student.list.StudentListFragment

class StudentRouter(activity: StudentActivity) : BaseRouter<StudentActivity>(activity) {
    fun goToStudentList() {
        replaceFragment(activity.supportFragmentManager, StudentListFragment.getInstance(), R.id.container, false)
    }

    fun goToStudentDetails(id: String) {
        val containerDetails = activity.findViewById<View>(R.id.containerDetails)
        if (containerDetails == null) {
            replaceFragment(activity.supportFragmentManager, StudentDetailsFragment.getInstance(id), R.id.container, true)
        } else {
            replaceFragment(activity.supportFragmentManager, StudentDetailsFragment.getInstance(id),
                    R.id.containerDetails, false)
        }

    }

    fun goToStudentAdd() {
        replaceFragment(activity.supportFragmentManager, StudentAddFragment.getInstance(), R.id.container, true)
    }
}