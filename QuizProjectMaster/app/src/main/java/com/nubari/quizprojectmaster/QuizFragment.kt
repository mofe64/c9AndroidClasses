package com.nubari.quizprojectmaster

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.nubari.quizprojectmaster.databinding.FragmentQuizBinding


class QuizFragment : Fragment() {

    private lateinit var quizFragmentBinding: FragmentQuizBinding
    private lateinit var questionsList: List<Question>
    private var currentQuestion: Int = 1
    private var selectedOption: Int = -1
    private var optionButtons: List<Button>? = null
    private var quizComplete: Boolean = false
    private var score: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.let {
            val data = it["gameData"] as Bundle
            score = data.getInt("score")
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        var bundle = bundleOf(
            "questionObj" to questionsList[currentQuestion - 1],
            "score" to 6
        )
        outState.putBundle("gameData", bundle)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        quizFragmentBinding = FragmentQuizBinding.inflate(
            inflater,
            container,
            false
        )
        /**
         * If we used a bundle to pass args to this frag while navigating, then
         * We could access nav args as follows
         * **/
//        val name = arguments?.get("name")
        /**
         * Using safe args we can retrieve our args like this
         * **/
        var name = ""
        arguments?.let {
            name = QuizFragmentArgs.fromBundle(it).username
        }

        val welcomeTextWithPlaceholders = resources.getString(R.string.welcome_banner_text)
        val welcomeText = String.format(welcomeTextWithPlaceholders, name)
        val welcomeBanner = quizFragmentBinding.quizBanner
        welcomeBanner.text = welcomeText

        return quizFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        questionsList = Util.generateQuestions()
        currentQuestion = 1

        val progressBar = quizFragmentBinding.progressBar
        progressBar.progress = currentQuestion
        val progressBarTextWithPlaceHolders = resources.getString(R.string.progress_bar_text)
        val progressBarText = String.format(
            progressBarTextWithPlaceHolders,
            currentQuestion.toString(),
            questionsList.size
        )
        val progressBarLabel = quizFragmentBinding.progressText
        progressBarLabel.text = progressBarText

        val submitButton = quizFragmentBinding.submitBtn
        prepareQuestions()

        submitButton.setOnClickListener {
            if (!quizComplete) {
                val userAnswer = selectedOption
                if (userAnswer == -1) {
                    Toast.makeText(activity, "Please select an answer", Toast.LENGTH_SHORT).show()
                } else {
                    val currentQuestionIndex = currentQuestion - 1
                    val currentQuestionObj = questionsList[currentQuestionIndex]
                    if (userAnswer == currentQuestionObj.correctAnswerNumber) {
                        score++
                    }
                }
                currentQuestion++
                if (currentQuestion <= questionsList.size) {
                    updateProgress()
                    prepareQuestions()
                } else {
                    submitButton.text = resources.getString(R.string.view_score)
                    quizComplete = true
                }
            } else {

                val action = QuizFragmentDirections.actionQuizFragmentToResultFragment(
                    score = score,
                    questionSize = questionsList.size
                )
                Log.i("score", "score $score")
                findNavController().navigate(action)
            }

        }


    }


    private fun updateProgress() {
        val progressBar = quizFragmentBinding.progressBar
        progressBar.progress = currentQuestion
        val progressBarTextWithPlaceHolders = resources.getString(R.string.progress_bar_text)
        Log.i("quiz", "size ${questionsList.size}")
        val progressBarText = String.format(
            progressBarTextWithPlaceHolders,
            currentQuestion.toString(),
            questionsList.size
        )
        val progressBarLabel = quizFragmentBinding.progressText
        progressBarLabel.text = progressBarText

    }

    private fun prepareQuestions() {
        optionButtons?.let {
            if (currentQuestion != 1) {
                resetActiveOptions(it)
            }
        }

        currentQuestion
        val questionIndex = currentQuestion - 1
        val questionObj = questionsList[questionIndex]

        val question = quizFragmentBinding.question
        val optionOne = quizFragmentBinding.optionOne
        val optionTwo = quizFragmentBinding.optionTwo
        val optionThree = quizFragmentBinding.optionThree
        val optionFour = quizFragmentBinding.optionFour
        question.text = questionObj.questionText
        optionOne.text = questionObj.optionOne
        optionTwo.text = questionObj.optionTwo
        optionThree.text = questionObj.optionThree
        optionFour.text = questionObj.optionFour

        optionButtons = listOf(optionOne, optionTwo, optionThree, optionFour)

        optionOne.setOnClickListener {
            setActiveOption(optionButtons!!, 1)
        }

        optionTwo.setOnClickListener {
            setActiveOption(optionButtons!!, 2)
        }
        optionThree.setOnClickListener {
            setActiveOption(optionButtons!!, 3)
        }
        optionFour.setOnClickListener {
            setActiveOption(optionButtons!!, 4)
        }

    }

    private fun setActiveOption(options: List<Button>, selectionOptionNumber: Int) {
        val selectedIndex = selectionOptionNumber - 1
        options.forEachIndexed { index, button ->
            if (index == selectedIndex) {
                selectedOption = selectionOptionNumber
                button.setBackgroundColor(Color.BLACK)
                button.setTextColor(Color.WHITE)
            } else {
                button.setBackgroundColor(Color.WHITE)
                button.setTextColor(Color.BLACK)
            }
        }
    }

    private fun resetActiveOptions(options: List<Button>) {
        selectedOption = -1
        options.forEach { button ->
            button.setBackgroundColor(Color.WHITE)
            button.setTextColor(Color.BLACK)
        }
    }


}

