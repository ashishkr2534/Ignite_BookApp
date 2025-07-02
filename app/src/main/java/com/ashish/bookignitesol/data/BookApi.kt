package com.ashish.ignitebookapp.data

import com.ashish.ignitebookapp.models.BookResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Ashish Kr on 02,July,2025
 */
// ---- BookApi.kt ----
interface BookApi {
    @GET("books")
    suspend fun getBooks(
        @Query("topic") genre: String,
        @Query("mime_type") mimeType: String = "image/"
    ): BookResponse
}
//interface BookApi {
//    @GET("books")
//    suspend fun getBooks(): BookResponse
//}
//interface ApiService {
//    @GET("random_joke")
//    suspend fun getJokes(): Joke
//
//
//}

