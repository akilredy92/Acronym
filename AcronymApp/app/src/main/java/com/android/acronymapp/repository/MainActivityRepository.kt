package com.android.acronymapp.repository

import androidx.lifecycle.MutableLiveData
import com.android.acronymapp.model.AcromineResponse
import com.android.acronymapp.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MainActivityRepository {

    private val serviceToGetAcronyms = MutableLiveData<List<AcromineResponse>>()

    fun getAcronymsApiCall(searchAcronym: String): MutableLiveData<List<AcromineResponse>> {

        val call = RetrofitClient.apiInterface.getAcronyms(searchAcronym)

        call.enqueue(object : Callback<List<AcromineResponse>> {
            override fun onResponse(
                call: Call<List<AcromineResponse>>,
                response: Response<List<AcromineResponse>>
            ) {
                serviceToGetAcronyms.value = response.body()
            }

            override fun onFailure(call: Call<List<AcromineResponse>>, t: Throwable) {
                serviceToGetAcronyms.value = listOf()
            }

        })

        return serviceToGetAcronyms
    }
}