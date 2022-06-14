package com.nubari.quizprojectmaster

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.nubari.quizprojectmaster.databinding.FragmentWelcomeBinding


class WelcomeFragment : Fragment() {

    private lateinit var welcomeFragmentBinding: FragmentWelcomeBinding

    /** We do not perform any operations during the on create call back so we comment this out **/
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        /**
        This is how we inflate the layout for our fragment
        However because we want to use view binding we do not use this method
         **/
//        return inflater.inflate(R.layout.fragment_welcome, container, false)
        /** To initialize our view binding object and inflate our layout, we do the following **/
        welcomeFragmentBinding = FragmentWelcomeBinding.inflate(
            inflater,
            container,
            false
        )

        // get refs to views
        val nameInput = welcomeFragmentBinding.nameInput
        val startButton = welcomeFragmentBinding.startBtn

        // set up on click listeners
        startButton.setOnClickListener {
            val username = nameInput.text.toString()
            if (username.isEmpty()) {
                Toast.makeText(activity, "Please provide a valid name", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            /**
             * We could pass our argument using  a bundle as seen below, but that is not null safe
             * Instead we opted to use the safeargs
             *
             * **/
//            val bundle = bundleOf(
//                "name" to username
//            )
//
//            findNavController().navigate(
//                R.id.action_welcomeFragment_to_quizFragment,
//                bundle
//            )

            /** To use safe args  we need to add the deps to our classpath
             * In our project build.gradle
             * Then in our app build.gradle, we add the safe args plug in
             * When using the safe args we use the generated directions class
             * Note the directions class will only be available after we build the project
             * **/
            val action = WelcomeFragmentDirections.actionWelcomeFragmentToQuizFragment(
                username = username
            )
            findNavController().navigate(action)

        }


        return welcomeFragmentBinding.root
    }


}