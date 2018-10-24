package com.gmail.vitaliklancer.data.db.entity

import com.gmail.vitaliklancer.data.entity.StudentResponse
import com.gmail.vitaliklancer.domain.entity.Student


fun StudentDB.transformToDomain(): Student {
    return Student(id = id, name = name, age = age, gender = gender, imgUrl = imgUrl)
}

fun StudentResponse.transformToDB(): StudentDB {
    return StudentDB(id = id, name = name, age = age, gender = gender, imgUrl = imgUrl)
}