package com.udacity.shoestore.shoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeViewModel : ViewModel() {


    private val _navigateToShoeDetail = MutableLiveData<Boolean>()
    val navigateToShoeDetail: LiveData<Boolean>
        get() = _navigateToShoeDetail

    private val shoes = mutableListOf<Shoe>()

    private val _shoeList = MutableLiveData<List<Shoe>>()
    val shoeList: LiveData<List<Shoe>>
        get() = _shoeList

    private val _navigateToLogin = MutableLiveData<Boolean>()
    val navigateToLogin: LiveData<Boolean>
        get() = _navigateToLogin

    private val _navigateToShoeListing = MutableLiveData<Boolean>()
    val navigateToShoeListing: LiveData<Boolean>
        get() = _navigateToShoeListing

    private val _shoeItemEmpty = MutableLiveData<Boolean>()
    val shoeItemEmpty: LiveData<Boolean>
        get() = _shoeItemEmpty

    val name = MutableLiveData<String>()
    val company = MutableLiveData("")
    val size = MutableLiveData("")
    val description = MutableLiveData("")

    fun onFabClicked() {
        _navigateToShoeDetail.value = true
    }

    fun onCancelClicked() {
        _navigateToShoeListing.value = true
    }

    fun onSaveClicked() {

        if (
            validateShoeItem()
        ) {
            Timber.d(name.value.toString())
            _shoeItemEmpty.value = true
        } else {
            val shoeItem = Shoe(
                name = name.value!!,
                size = size.value!!,
                company = company.value!!,
                description = description.value!!
            )
            shoes.add(shoeItem)
            _shoeList.value = shoes
            Timber.d(_shoeList.value.toString())
           _navigateToShoeListing.value = true
        }

    }

    fun onNavigatedToShoeListing() {
        _navigateToShoeListing.value = false
    }


    fun onNavigatedShoeDetail() {
        _navigateToShoeDetail.value = false
    }

    fun shoeItemReset() {
        _shoeItemEmpty.value = false
    }

    private fun validateShoeItem(): Boolean {
        return name.value.isNullOrEmpty() || size.value.isNullOrEmpty() || company.value.isNullOrEmpty() || description.value.isNullOrEmpty()
    }

}