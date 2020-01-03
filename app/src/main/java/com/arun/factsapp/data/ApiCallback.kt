package com.arun.factsapp.data

interface ApiCallback {

    fun onSuccess(successResponse: Any?)
    fun onFail(failedResponse: Any?)

}
