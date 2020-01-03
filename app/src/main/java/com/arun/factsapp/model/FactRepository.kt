package com.arun.factsapp.model

import com.arun.factsapp.data.ApiCallback
import com.arun.factsapp.data.ApiClient
import com.arun.factsapp.data.FactResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FactRepository : FactDataSource {

    private var call = ApiClient.build()?.getFacts()


    override fun retrieveFacts(callback: ApiCallback) {
        call?.clone()?.enqueue(object : Callback<FactResponse> {
            override fun onFailure(call: Call<FactResponse>, t: Throwable) {
                callback.onFail(t.message)
            }

            override fun onResponse(call: Call<FactResponse>, response: Response<FactResponse>) {
                if (response.isSuccessful) {
                    callback.onSuccess(response.body())
                } else {
                    callback.onFail(response.errorBody())
                }
            }

        })
    }

    override fun cancelApi() {
        call?.cancel()
    }

}