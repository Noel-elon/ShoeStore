package com.udacity.shoestore.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private val _navigateToOnBoarding = MutableLiveData<Boolean>()
    val navigateToOnBoarding: LiveData<Boolean>
        get() = _navigateToOnBoarding

    fun onLoginClicked() {
        _navigateToOnBoarding.value = true
    }
    fun onSignInClicked() {
        _navigateToOnBoarding.value = true
    }

    fun onNavigatedToOnBoarding(){
        _navigateToOnBoarding.value = false
    }
}