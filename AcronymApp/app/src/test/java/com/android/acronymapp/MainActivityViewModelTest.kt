package com.android.acronymapp

//import androidx.arch.core.executor.TaskExecutortesting.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.android.acronymapp.model.AcromineResponse
import com.android.acronymapp.viewmodel.MainActivityViewModel
import junit.framework.TestCase
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class MainActivityViewModelTest : TestCase() {

//    @Rule
//    @JvmField
//    val rule = InstantTaskExecutorRule()

    private val viewModel by lazy { MainActivityViewModel() }

    private fun setValues(value: List<AcromineResponse>) {
        viewModel.listOfAcromines.value = value
    }

    @Test
    fun plus() {
        // given
        val givenVal = mutableListOf<AcromineResponse>()
        setValues(givenVal)

        // when

        // then
        Assert.assertEquals("2", viewModel.getAcronyms("HMM").value)
    }


    @Test
    fun clear() {
        // given
        val givenVal = mutableListOf<AcromineResponse>()
        setValues(givenVal)

        // when

        // then
        Assert.assertEquals("", viewModel.listOfAcromines.value)
    }
}