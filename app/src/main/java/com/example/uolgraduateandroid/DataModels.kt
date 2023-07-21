package com.example.uolgraduateandroid

import java.io.Serializable

data class Lecture(
    val lectureID: Int,
    val lectureName: String,
    val lectureIconURL: String,
    val lectureDurationMinutes: Int,
    val topics: List<Int>
)  : Serializable

data class Topic(
    val topicID: Int,
    val topicName: String
)  : Serializable