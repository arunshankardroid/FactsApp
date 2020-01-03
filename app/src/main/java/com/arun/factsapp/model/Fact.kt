package com.arun.factsapp.model

data class Fact(val title: String?, val description: String?, val imageHref: String?) {
    fun isValid() : Boolean {
        return (title != null || description != null || imageHref != null)
    }
}