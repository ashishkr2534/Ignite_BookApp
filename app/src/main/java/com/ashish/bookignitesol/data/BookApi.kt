package com.ashish.ignitebookapp.data

import com.ashish.ignitebookapp.models.BookResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Ashish Kr on 02,July,2025
 */

interface BookApi {
    @GET("books/")
    suspend fun getBooks(): BookResponse

        @GET("books")
    suspend fun getBooksSearch(
        @Query("topic") genre: String,
        @Query("mime_type") mimeType: String = "image/"
    ): BookResponse


    @GET("books/")
    suspend fun getBooksByTopicAndMime(
    @Query("topic") topic: String,
    @Query("mime_type") mimeType: String = "image/",
    @Query("page") page: Int
    ): BookResponse


}
