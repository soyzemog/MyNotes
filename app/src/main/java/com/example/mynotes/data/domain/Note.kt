package com.example.mynotes.data.domain


data class Note(
    val id: Int? = null,
    val title: String? = null,
    val subtitle: String? = null,
    val date: String? = null,
    val image: String? = null,
    val link: String? = null,
    val size: Int? = null,
    val color: Int? = null
)