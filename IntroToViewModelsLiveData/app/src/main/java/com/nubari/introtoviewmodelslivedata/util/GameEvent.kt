package com.nubari.introtoviewmodelslivedata.util

sealed class GameEvent {
    data class IncrementEvent(val point: Int) : GameEvent()
    data class DecrementEvent(val point: Int) : GameEvent()
}




