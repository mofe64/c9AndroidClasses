package com.nubari.introtoviewmodelslivedata.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.nubari.introtoviewmodelslivedata.databinding.FragmentHomeBinding
import com.nubari.introtoviewmodelslivedata.util.GameEvent
import com.nubari.introtoviewmodelslivedata.viewmodels.HomeViewModel


class Home : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val homeViewModel: HomeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(
            inflater,
            container,
            false
        )

        binding.scoreTextView.text = homeViewModel.scoreState.value.toString()
        homeViewModel.scoreState.observe(viewLifecycleOwner, { newScoreState ->
            newScoreState?.let {newScoreValue ->
                binding.scoreTextView.text = newScoreValue.toString()
            }

        })

        binding.one.setOnClickListener {
            val event = GameEvent.IncrementEvent(1)
            homeViewModel.onEvent(event = event)
        }
        binding.five.setOnClickListener {
            val event = GameEvent.IncrementEvent(5)
            homeViewModel.onEvent(event = event)
        }
        binding.ten.setOnClickListener {
            val event = GameEvent.DecrementEvent(10)
            homeViewModel.onEvent(event = event)
        }
        return binding.root

    }


}