package com.ashish.ignitebookapp.data

import android.util.Log
import com.ashish.ignitebookapp.models.Book
import javax.inject.Inject

/**
 * Created by Ashish Kr on 02,July,2025
 */

class BookRepository @Inject constructor(private val api: ApiInterface) {

    //get all books -- check  Api call
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

//    suspend fun getBooksSearch(genre: String): List<Book> {
//        return try {
////            val response = api.getBooks()
//            val response = api.getBooksSearch(genre = genre, mimeType = "image/")
//            Log.d("ResultBooks Search", response.results.toString())
//            response.results
//        } catch (e: Exception) {
//            Log.e("BookRepository", "Error fetching books", e)
//            emptyList()
//        }
//    }

    //fetch books by genre
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

    //check if search query matches with or in contains in title or author
    suspend fun searchBooksInGenre(genre: String, query: String): List<Book> {
        return try {
            val allBooks = api.getBooksByTopicAndMime(topic = genre.lowercase(), page = 1).results
            allBooks.filter {
                it.title.contains(query, ignoreCase = true) ||
                        it.authors.any { author -> author.name.contains(query, ignoreCase = true) }
            }
        } catch (e: Exception) {
            Log.e("BookRepository", "Error searching books in genre", e)
            emptyList()
        }
    }

}