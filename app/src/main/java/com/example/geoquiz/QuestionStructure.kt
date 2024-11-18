package com.example.geoquiz

data class QuestionStructure(
    val id: Int,
    val question: String,
    val image: Int,
    val optOne: String,
    val optTwo: String,
    val optThree: String,
    val optFour: String,
    val answerCorrect: Int
)
