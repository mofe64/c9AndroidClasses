package com.nubari.quizapplicationactual

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.nubari.quizapplicationactual.databinding.FragmentQuizBinding


class QuizFragment : Fragment() {

    private lateinit var quizFragmentBinding: FragmentQuizBinding
    private val arguments : QuizFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        quizFragmentBinding = FragmentQuizBinding.inflate(
            inflater,
            container,
            false
        )

        val name = arguments.name
        val welcomeTextWithPlaceholders = resources.getString(R.string.welcome_banner_text)
        val welcomeText = String.format(welcomeTextWithPlaceholders, name)
        val welcomeBanner = quizFragmentBinding.quizBanner
        welcomeBanner.text = welcomeText


        return quizFragmentBinding.root
    }


}