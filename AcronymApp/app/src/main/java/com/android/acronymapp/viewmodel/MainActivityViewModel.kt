package com.android.acronymapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.acronymapp.model.AcromineResponse
import com.android.acronymapp.repository.MainActivityRepository

class MainActivityViewModel : ViewModel() {

    var listOfAcromines = MutableLiveData<List<AcromineResponse>>()

    init {
        listOfAcromines.value = listOf()
    }

    fun getAcronyms(searchAcronym: String): LiveData<List<AcromineResponse>> {
        listOfAcromines = MainActivityRepository.getAcronymsApiCall(searchAcronym)
        return listOfAcromines
    }

}