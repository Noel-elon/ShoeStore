package com.udacity.shoestore.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OnBoardingViewModel : ViewModel() {

    private val _navigateToShoeList = MutableLiveData<Boolean>()
    private val _navigateToInstructions = MutableLiveData<Boolean>()
    val navigateToInstructions: LiveData<Boolean>
        get() = _navigateToInstructions

    val navigateToShoeList: LiveData<Boolean>
        get() = _navigateToShoeList


    fun onNextClicked() {
        _navigateToInstructions.value = true
    }

    fun onBeginClicked() {
        _navigateToShoeList.value = true
    }

    fun onNavigatedToInstructions() {
        _navigateToInstructions.value = false
    }

    fun onNavigatedToShoeList() {
        _navigateToShoeList.value = false
    }
}