package com.ashish.ignitebookapp.data

import com.ashish.ignitebookapp.models.Book
import javax.inject.Inject

/**
 * Created by Ashish Kr on 02,July,2025
 */
class BookRepository @Inject constructor(private val api: BookApi) {
    suspend fun getBooksByGenre(genre: String): List<Book> {
        return try {
            val response = api.getBooks(genre = genre, mimeType = "image/")
            response.results
        } catch (e: Exception) {
            emptyList()
        }
    }
}