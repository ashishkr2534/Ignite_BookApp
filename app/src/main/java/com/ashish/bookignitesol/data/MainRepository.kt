package com.ashish.ignitebookapp.data

import android.util.Log
import com.ashish.ignitebookapp.models.Book
import javax.inject.Inject

/**
 * Created by Ashish Kr on 02,July,2025
 */

class BookRepository @Inject constructor(private val api: BookApi) {
    suspend fun getBooks(): List<Book> {
        return try {
            val response = api.getBooks()
            Log.d("ResultBooks", response.results.toString())
            response.results
        } catch (e: Exception) {
            Log.e("BookRepository", "Error fetching books", e)
            emptyList()
        }
    }

    suspend fun getBooksSearch(genre: String): List<Book> {
        return try {
//            val response = api.getBooks()
            val response = api.getBooksSearch(genre = genre, mimeType = "image/")
            Log.d("ResultBooks Search", response.results.toString())
            response.results
        } catch (e: Exception) {
            Log.e("BookRepository", "Error fetching books", e)
            emptyList()
        }
    }

suspend fun getBooksByGenre(genre: String, page: Int): List<Book> {
    return try {
        val response = api.getBooksByTopicAndMime(topic = genre.lowercase(), page = page)
        Log.d("PageResult", "Page $page: ${response.results.size} books")
        response.results
    } catch (e: Exception) {
        Log.e("BookRepository", "Error loading page $page", e)
        emptyList()
    }
}
}