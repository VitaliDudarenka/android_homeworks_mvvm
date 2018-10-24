package com.gmail.vitaliklancer.homeworksnew.presentation.screen.student.add

import android.databinding.BindingAdapter
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.util.Log
import android.widget.ImageView
import com.gmail.vitaliklancer.domain.entity.Student
import com.gmail.vitaliklancer.homeworksnew.R
import com.gmail.vitaliklancer.homeworksnew.presentation.base.BaseViewModel
import com.gmail.vitaliklancer.homeworksnew.presentation.factory.UseCaseProvider
import com.gmail.vitaliklancer.homeworksnew.presentation.screen.student.StudentRouter
import com.squareup.picasso.Picasso
import io.reactivex.rxkotlin.subscribeBy
import jp.wasabeef.picasso.transformations.CropCircleTransformation

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, imageUrl: String) {
    if (imageUrl.isEmpty()) return

    Picasso.get()
            .load(imageUrl)
            .transform(CropCircleTransformation())
            .placeholder(R.drawable.progress_animation)
            .into(view)

}

class StudentAddViewModel : BaseViewModel<StudentRouter>() {
    val name = ObservableField<String>("")
    val imgUrl = ObservableField<String>("")
    val age = ObservableField<String>("")
    val gender = ObservableField<Boolean>(true)
    private val addStudentUseCase = UseCaseProvider.provideAddStudentUseCase()
    val isProgressEnabled = ObservableBoolean(false)

    init {
        Log.e("gender", gender.get().toString())
    }

    fun onClickSave() {
        val student = Student(id = System.currentTimeMillis().toString(), age = Integer.parseInt(age.get()),
                name = name.get()!!, gender = gender.get()!!, imgUrl = imgUrl.get()!!)
        val disposable = addStudentUseCase.add(student).subscribeBy(
                onError = {
                    isProgressEnabled.set(false)
                    router?.showError(it)
                }
        )
        addToDisposable(disposable)
        router!!.goToStudentList()
    }

    override fun onCleared() {
    }

    fun genderFemale() {
        gender.set(false)
        Log.e("gender", gender.get().toString())
    }

    fun genderMale() {
        gender.set(true)
        Log.e("gender", gender.get().toString())
    }
}