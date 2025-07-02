package com.ashish.ignitebookapp.data

import android.util.Log
import com.ashish.ignitebookapp.models.Book
import javax.inject.Inject

/**
 * Created by Ashish Kr on 02,July,2025
 */
//class BookRepository @Inject constructor(private val api: BookApi) {
//    suspend fun getBooksByGenre(genre: String): List<Book> {
//        return try {
////            val response = api.getBooks(genre = genre, mimeType = "image/")
//            val response = api.getBooks()
//            response.results
//            Log.d("Result Books", response.results)
//        } catch (e: Exception) {
//            emptyList()
//        }
//    }
//}

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
}