package com.gmail.vitaliklancer.homeworksnew.presentation.screen.student.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.gmail.vitaliklancer.domain.entity.Student
import com.gmail.vitaliklancer.homeworksnew.R
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import java.util.*
import kotlin.collections.ArrayList


class StudentListAdapter : RecyclerView.Adapter<StudentListAdapter.Holder>() {
    private var studlist: ArrayList<Student>? = ArrayList()
    var listData: MutableList<Student>? = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
            studlist!!.addAll(this.listData!!)
        }
    internal var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): Holder {
        var view = LayoutInflater.from(viewGroup.context).inflate(R.layout.student_item, viewGroup, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = listData!!.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val student = listData!![position]
        holder.nameTextView.text = student.name
        holder.ageTextView.text = student.age.toString() ?: ""
        if (!student.imgUrl.isEmpty())
        Picasso.get().load(student.imgUrl).transform(CropCircleTransformation()).into(holder.avaImageView)
    }

    inner class Holder : RecyclerView.ViewHolder {
        var nameTextView: TextView
        var ageTextView: TextView
        var avaImageView: ImageView

        constructor(view: View) : super(view) {
            nameTextView = view.findViewById(R.id.nameTextView)
            ageTextView = view.findViewById(R.id.ageTextView)
            avaImageView = view.findViewById(R.id.studImageView)
            itemView.setOnClickListener {
                val student = listData!![layoutPosition]
                onItemClickListener!!.onItemClick(student)
            }
        }

    }

    interface OnItemClickListener {
        fun onItemClick(student: Student)
    }

    fun filter(charText: String) {
        var charText = charText
        charText = charText.toLowerCase(Locale.getDefault())
        listData!!.clear()
        if (charText.isEmpty()) {
            listData!!.addAll(this.studlist!!)
        } else {
            for (student in this.studlist!!) {
                if ((student.name.toLowerCase(Locale.getDefault()) + " " + student.name.toLowerCase(Locale.getDefault())).contains(charText)) {
                    listData!!.add(student)
                }
            }
        }
        notifyDataSetChanged()
    }
}