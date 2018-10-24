package com.gmail.vitaliklancer.homeworksnew.presentation.screen.student.details

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.gmail.vitaliklancer.homeworksnew.R
import com.gmail.vitaliklancer.homeworksnew.databinding.FragmentStudentDetailsBinding
import com.gmail.vitaliklancer.homeworksnew.presentation.base.BaseMvvmFragment
import com.gmail.vitaliklancer.homeworksnew.presentation.screen.student.StudentRouter
import com.gmail.vitaliklancer.homeworksnew.presentation.utils.visibility


class StudentDetailsFragment : BaseMvvmFragment<StudentDetailsViewModel, StudentRouter, FragmentStudentDetailsBinding>() {
    companion object {
        private const val ID_EXTRA = "ID_EXTRA"
        fun getInstance(id: String): StudentDetailsFragment {
            val fragment = StudentDetailsFragment()
            val bundle = Bundle()
            bundle.putString(ID_EXTRA, id)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun provideViewModel(): StudentDetailsViewModel {
        return ViewModelProviders.of(this).get(StudentDetailsViewModel::class.java)
    }

    override fun provideLayoutId(): Int = R.layout.fragment_student_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getString(ID_EXTRA)
        if (id != null) {
            viewModel.setStudentId(id)
        } else {
            router?.goBack()
        }
        val viewOpacity: View = view.findViewById(R.id.view1)
        val editTextName: EditText = view.findViewById(R.id.nameEditDetails)
        val editTextAge: EditText = view.findViewById(R.id.ageEditDetails)
        val help1: TextView = view.findViewById(R.id.textHelp1)
        val help2: TextView = view.findViewById(R.id.textHelp2)
        val help3: TextView = view.findViewById(R.id.textHelp3)
        viewOpacity.bringToFront()
        editTextName.bringToFront()
        help1.visibility(true)
        help1.bringToFront()
        var count = 0
        view.setOnClickListener {
            when (count) {
                0 -> {
                    viewOpacity.bringToFront()
                    help1.visibility(false)
                    editTextAge.bringToFront()
                    help2.visibility(true)
                    help2.bringToFront()
                    count++
                }
                1 -> {
                    viewOpacity.bringToFront()
                    help2.visibility(false)
                    help3.visibility(true)
                    help3.bringToFront()
                    count++
                }
                2 -> {
                    viewOpacity.visibility(false)
                    help3.visibility(false)

                }
            }
        }
    }


}