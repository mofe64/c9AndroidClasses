package com.nubari.quizprojectmaster

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.nubari.quizprojectmaster.databinding.FragmentResultBinding


class ResultFragment : Fragment() {
    private lateinit var resultFragmentBinding: FragmentResultBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        resultFragmentBinding = FragmentResultBinding.inflate(
            inflater,
            container,
            false
        )
        var score = 0
        var questionSize = 0
        arguments?.let {
            score = ResultFragmentArgs.fromBundle(it).score
            questionSize = ResultFragmentArgs.fromBundle(it).questionSize
        }
        val scoreWithPlaceholders = resources.getString(R.string.score)
        val formattedScore = String.format(
            scoreWithPlaceholders,
            score.toString(), questionSize.toString()
        )

        val scoreView = resultFragmentBinding.score
        scoreView.text =formattedScore

        val playAgainButton = resultFragmentBinding.playAgainBtn
        playAgainButton.setOnClickListener {
            val action = ResultFragmentDirections.actionResultFragmentToWelcomeFragment()
            findNavController().navigate(action)
        }

        return resultFragmentBinding.root
    }

}