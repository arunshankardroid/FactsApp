package com.arun.factsapp.ui.main.viewmodel

import com.arun.factsapp.model.Fact

data class FactResponseUI(val isLoading: Boolean, val facts: List<Fact>, val title: String = "", val isEmpty: Boolean = false, val isError: Boolean = false)



