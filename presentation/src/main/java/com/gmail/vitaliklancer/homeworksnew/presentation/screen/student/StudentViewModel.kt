package com.gmail.vitaliklancer.homeworksnew.presentation.screen.student

import android.databinding.ObservableField
import com.gmail.vitaliklancer.homeworksnew.presentation.base.BaseViewModel
import com.gmail.vitaliklancer.homeworksnew.presentation.factory.UseCaseProvider
import io.reactivex.rxkotlin.subscribeBy
import com.gmail.vitaliklancer.homeworksnew.R
import com.squareup.picasso.Picasso
import java.util.*
import android.databinding.BindingAdapter
import android.widget.ImageView
import android.util.Log
import jp.wasabeef.picasso.transformations.CropCircleTransformation


/*@BindingAdapter("bind:imageUrl")
fun loadImage(view: ImageView, imageUrl: String) {
    Log.e("AAA", imageUrl)
    Picasso.get()
            .load(imageUrl)
            .transform(CropCircleTransformation())
            .placeholder(R.drawable.progress_animation)
            .into(view)

}*/

class StudentViewModel : BaseViewModel<StudentRouter>() {
   /* val name = ObservableField<String>("No data")
    private val imgUrl = ObservableField<String>("No data")
    val age = ObservableField<String>("No data")
    val gender = ObservableField<Boolean>(true)
    private val getStudentUseCase = UseCaseProvider.provideGetStudentUseCase()
    private val random = Random()


    init {
        val disposable = getStudentUseCase.get()
                .subscribeBy(
                        onNext = {
                            val randomId = random.nextInt(it.size)
                            name.set(it[randomId].name)
                            age.set(it[randomId].age.toString())
                            imgUrl.set(it[randomId].imgUrl)
                            gender.set(it[randomId].gender)
                            Log.e("BBB", imgUrl.get())
                        },
                        onError = {
                            name.set("onError = " + it.toString())
                        })

        addToDisposable(disposable)
    }

    override fun onCleared() {
    }


    fun getImageUrl(): String? {
        return imgUrl.get()
    }*/
}