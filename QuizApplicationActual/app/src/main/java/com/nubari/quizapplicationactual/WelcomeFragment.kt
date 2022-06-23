package com.nubari.quizapplicationactual

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.nubari.quizapplicationactual.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {
    private lateinit var welcomeFragmentBinding: FragmentWelcomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i("test", "test")
        welcomeFragmentBinding = FragmentWelcomeBinding.inflate(
            inflater,
            container,
            false
        )
        val startGameButton = welcomeFragmentBinding.startBtn
        val userNameInput = welcomeFragmentBinding.nameInput
        startGameButton.setOnClickListener {
            val navController = findNavController()
            val username = userNameInput.text.toString()
            val action = WelcomeFragmentDirections
                .actionWelcomeFragment2ToQuizFragment(
                    name = username
                )

            navController.navigate(action)
        }


        return welcomeFragmentBinding.root
    }

}