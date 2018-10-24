package com.gmail.vitaliklancer.data.entity

import com.gmail.vitaliklancer.domain.entity.Student

fun StudentResponse.transformToDomain(): Student {
    return Student(id = id, name = name, age = age, gender = gender, imgUrl = imgUrl)
}

fun Student.transformToData(): StudentRequest {
    return StudentRequest(id = id, name = name, age = age, gender = gender, imgUrl = imgUrl)
}