package com.arun.factsapp.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arun.factsapp.model.FactDataSource

class ViewModelFactory(private val repository:FactDataSource):ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FactViewModel(repository) as T
    }
}