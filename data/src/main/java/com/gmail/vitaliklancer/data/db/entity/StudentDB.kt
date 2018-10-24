package com.gmail.vitaliklancer.data.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "student")
data class StudentDB(@PrimaryKey val id: String, val name: String, val age: Int, val gender: Boolean, val imgUrl: String) {
}