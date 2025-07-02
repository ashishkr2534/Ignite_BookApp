package com.ashish.ignitebookapp.models

data class BookResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Book>
)

data class Book(
    val id: Int,
    val title: String,
    val authors: List<Author>,
    val formats: Map<String, String>
)

data class Author(
    val name: String
)
