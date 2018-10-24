package com.gmail.vitaliklancer.data.entity

import com.google.gson.annotations.SerializedName

class StudentDeleteRequest(@SerializedName("id")
                           val id: String) : DataEntity {
}