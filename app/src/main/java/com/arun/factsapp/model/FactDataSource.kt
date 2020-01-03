package com.arun.factsapp.model

import com.arun.factsapp.data.ApiCallback

interface FactDataSource {

    fun retrieveFacts(callback: ApiCallback)
    fun cancelApi()
}