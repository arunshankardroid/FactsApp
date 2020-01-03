package com.arun.factsapp.data

import com.arun.factsapp.model.Fact

data class FactResponse(val title: String, val rows: List<Fact>)