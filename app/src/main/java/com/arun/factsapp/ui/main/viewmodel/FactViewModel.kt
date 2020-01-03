package com.arun.factsapp.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arun.factsapp.data.ApiCallback
import com.arun.factsapp.data.FactResponse
import com.arun.factsapp.model.FactDataSource

class FactViewModel(private val repository: FactDataSource) : ViewModel() {

    open val _response = MutableLiveData<FactResponseUI>()
    val response : LiveData<FactResponseUI> = _response

    fun getFacts() {
        _response.postValue(
            FactResponseUI(
                true,
                emptyList()
            )
        )
        repository.retrieveFacts(object : ApiCallback{
            override fun onSuccess(successResponse: Any?) {
                successResponse?.let {
                    if(it is FactResponse) {
                        if(it.rows.isEmpty()) {
                            _response.postValue(
                                FactResponseUI(
                                    false,
                                    emptyList(),
                                    isEmpty = true
                                )
                            )
                        } else {
                            _response.postValue(
                                FactResponseUI(
                                    false,
                                    it.rows.filter { it.isValid() },
                                    it.title
                                )
                            )
                        }
                    }
                }
            }

            override fun onFail(failedResponse: Any?) {
                _response.postValue(
                    FactResponseUI(
                        false,
                        emptyList(),
                        isError = true
                    )
                )
            }

        })
    }


}
