package com.gmail.vitaliklancer.homeworksnew.presentation.screen.student.details

import android.content.Context
import android.content.res.Resources
import android.databinding.BindingAdapter
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.util.Log
import android.widget.ImageView
import com.gmail.vitaliklancer.homeworksnew.R
import com.gmail.vitaliklancer.homeworksnew.presentation.base.BaseViewModel
import com.gmail.vitaliklancer.homeworksnew.presentation.factory.UseCaseProvider
import com.gmail.vitaliklancer.homeworksnew.presentation.screen.student.StudentRouter
import com.squareup.picasso.Picasso
import io.reactivex.rxkotlin.subscribeBy
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import android.graphics.drawable.Drawable
import android.graphics.drawable.BitmapDrawable
import android.graphics.Bitmap
import com.gmail.vitaliklancer.domain.entity.Student
import com.squareup.picasso.Target
import java.lang.Exception

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, imageUrl: String) {
    if (imageUrl.isEmpty()) return

    Picasso.get()
            .load(imageUrl)
            .transform(CropCircleTransformation())
            .placeholder(R.drawable.progress_animation)
            .into(view)

}

class StudentDetailsViewModel : BaseViewModel<StudentRouter>() {
    val name = ObservableField<String>("No data")
    val imgUrl = ObservableField<String>("")
    val age = ObservableField<String>("No data")
    val gender = ObservableField<Boolean>(true)
    private var studentId: String? = null
    private val studentUseCase = UseCaseProvider.provideStudentUseCase()
    private val updateStudentUseCase = UseCaseProvider.provideUpdateStudentUseCase()
    private val deleteStudentUseCase = UseCaseProvider.provideDeleteStudentUseCase()
    val isProgressEnabled = ObservableBoolean(false)


    fun setStudentId(id: String) {
        if (studentId != null) return
        studentId = id
        isProgressEnabled.set(true)
        val disposable = studentUseCase.getById(id).subscribeBy(
                onNext = {
                    name.set(it.name)
                    age.set(it.age.toString())
                    imgUrl.set(it.imgUrl)

                    gender.set(it.gender)
                    isProgressEnabled.set(false)
                },
                onError = {
                    isProgressEnabled.set(false)
                    router?.showError(it)
                }
        )
        addToDisposable(disposable)

    }

    fun onClickSave() {
        val student = Student(id = this.studentId!!, age = Integer.parseInt(age.get()),
                name = name.get()!!, gender = gender.get()!!, imgUrl = imgUrl.get()!!)
        val disposable = updateStudentUseCase.update(student).subscribeBy(
                onError = {
                    isProgressEnabled.set(false)
                    router?.showError(it)
                }
        )
        addToDisposable(disposable)
        router!!.goToStudentList()
    }

    fun onClickDelete() {
        val disposable = deleteStudentUseCase.delete(this.studentId!!).subscribeBy(
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

}
