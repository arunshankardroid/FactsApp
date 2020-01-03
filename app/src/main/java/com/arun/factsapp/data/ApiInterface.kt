package com.arun.factsapp.data

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("facts.json")
    fun getFacts(): Call<FactResponse>
}