package com.gmail.vitaliklancer.domain.entity

data class Student(val id: String, val name: String, val age: Int, val gender: Boolean, val imgUrl: String) : DomainEntity {

}