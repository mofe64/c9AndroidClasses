package com.nubari.quizprojectmaster

data class Question(
    val questionText: String,
    val optionOne: String,
    val optionTwo: String,
    val optionThree: String,
    val optionFour: String,
    val correctAnswerNumber: Int
)
