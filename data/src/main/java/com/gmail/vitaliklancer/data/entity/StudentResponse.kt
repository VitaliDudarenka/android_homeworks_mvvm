package com.gmail.vitaliklancer.data.entity

import com.google.gson.annotations.SerializedName

data class StudentResponse(@SerializedName("objectId")
                           val id: String,
                           @SerializedName("name")
                           val name: String,
                           @SerializedName("age")
                           val age: Int,
                           @SerializedName("gender")
                           val gender: Boolean,
                           @SerializedName("imgUrl")
                           val imgUrl: String) : DataEntity {
}