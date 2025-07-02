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

//data class BookResponse(
//    val count: Int,
//    val next: String?,
//    val previous: String?,
//    val results: List<Book>
//)
//
//data class Book(
//    val id: Int,
//    val title: String,
//    val authors: List<Author>,
//    val translators: List<Translator>?,
//    val subjects: List<String>?,
//    val bookshelves: List<String>?,
//    val languages: List<String>?,
//    val copyright: Boolean?,
//    val media_type: String?,
//    val formats: Map<String, String>,
//    val download_count: Int
//) {
//    val imageUrl: String?
//        get() = formats["image/jpeg"]
//}
//
//data class Author(
//    val name: String,
//    val birth_year: Int? = null,
//    val death_year: Int? = null
//)
//
//data class Translator(
//    val name: String,
//    val birth_year: Int? = null,
//    val death_year: Int? = null
//)
