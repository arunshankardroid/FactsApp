package com.arun.factsapp.di

import com.arun.factsapp.model.FactDataSource
import com.arun.factsapp.model.FactRepository

object FactRepoInjection {

    fun provideFactRepository() : FactDataSource {
        return FactRepository()
    }
}