package com.nubari.journal.home.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nubari.journal.data.model.Journal
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    val journals: MutableLiveData<MutableList<Journal>> = MutableLiveData(
        mutableListOf()
    )

    init {
        viewModelScope.launch {
            delay(5000)
            val x = mutableListOf(
                Journal("1", "My Life"),
                Journal("2", "My Love Life"),
                Journal("3", "My Life 2"),
                Journal("4", "My Love Life 2"),
                Journal("5", "Another Life"),
                Journal("6", "Test")
            )
            journals.value = x
        }

    }

    fun addJournal() {
        val x = Journal("7", "new Journal")
        val currentJournals = journals.value
        currentJournals?.add(x)
        journals.value = currentJournals!!

    }

}