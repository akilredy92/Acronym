package com.android.acronymapp.retrofit

import com.android.acronymapp.model.AcromineResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("/software/acromine/dictionary.py")
    fun getAcronyms(@Query("sf") query: String): Call<List<AcromineResponse>>
}