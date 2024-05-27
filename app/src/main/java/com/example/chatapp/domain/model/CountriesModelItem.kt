package com.example.chatapp.domain.model

data class CountriesModelItem(
    val currency: String,
    val dialCode: String,
    val flag: String,
    val iso2: String,
    val iso3: String,
    val name: String,
    val states: List<State>
)