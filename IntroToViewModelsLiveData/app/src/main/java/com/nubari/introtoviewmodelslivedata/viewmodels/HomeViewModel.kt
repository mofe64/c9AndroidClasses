package com.nubari.introtoviewmodelslivedata.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nubari.introtoviewmodelslivedata.util.GameEvent


class HomeViewModel : ViewModel() {

    val scoreState: MutableLiveData<Int> = MutableLiveData(
        100
    )

    fun onEvent(event: GameEvent) {
        when (event) {
            is GameEvent.IncrementEvent -> {
                val points = event.point
                Log.i("homeViewModel", "adding point(s) $points")
                scoreState.value = scoreState.value?.plus(points)
            }
            is GameEvent.DecrementEvent -> {
                val points = event.point
                Log.i("homeViewModel", "removing point(s) $points")
                scoreState.value = scoreState.value?.minus(points)
            }
        }
    }



}
