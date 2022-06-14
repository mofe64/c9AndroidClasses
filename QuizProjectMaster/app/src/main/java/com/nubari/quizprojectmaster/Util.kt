package com.nubari.quizprojectmaster

object Util {

    fun generateQuestions(): List<Question> {
        val question1 = Question(
            "some question",
            "option 1",
            "option 2",
            "option 3",
            "option 4",
            1
        )
        val question2 = Question(
            "some question",
            "option 1",
            "option 2",
            "option 3",
            "option 4",
            2
        )
        val question3 = Question(
            "some question",
            "option 1",
            "option 2",
            "option 3",
            "option 4",
            3
        )
        val question4 = Question(
            "some question",
            "option 1",
            "option 2",
            "option 3",
            "option 4",
            4
        )
        val question5 = Question(
            "some question",
            "option 1",
            "option 2",
            "option 3",
            "option 4",
            5
        )

        return listOf(question1, question2, question3, question4, question5)
    }
}