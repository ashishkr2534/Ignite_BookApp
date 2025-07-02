package com.ashish.ignitebookapp.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashish.ignitebookapp.data.BookRepository
import com.ashish.ignitebookapp.models.Book
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Ashish Kr on 02,July,2025
 */

//@HiltViewModel
//class BooksViewModel @Inject constructor(private val repo: BookRepository) : ViewModel() {
//    private val _books = MutableStateFlow<List<Book>>(emptyList())
//    val books: StateFlow<List<Book>> = _books
//
//    private val _isLoading = MutableStateFlow(false)
//    val isLoading: StateFlow<Boolean> = _isLoading
//
//    fun loadBooks(genre: String) {
//        viewModelScope.launch {
//            _isLoading.value = true
////            val result = repo.getBooksByGenre(genre)
//            val result = repo.getBooks()
//            Log.d("BooksViewModel", "Loaded ${result.toString()} books")
//            _books.value = result
//            _isLoading.value = false
//        }
//    }
//
//    fun loadBooksSearch(genre: String) {
//        viewModelScope.launch {
//            _isLoading.value = true
//            val result = repo.getBooksSearch(genre)
////            val result = repo.getBooks()
//            Log.d("BooksViewModel Search", "Loaded ${result.toString()} books")
//            _books.value = result
//            _isLoading.value = false
//        }
//    }
//
//    fun loadBooksByGenre(genre: String) {
//        viewModelScope.launch {
//            _isLoading.value = true
//            val result = repo.getBooksByGenre(genre)
//            Log.d("BooksViewModel", "Loaded ${result.size} books for $genre")
//            _books.value = result
//            _isLoading.value = false
//        }
//    }
//}

@OptIn(FlowPreview::class)
@HiltViewModel
class BooksViewModel @Inject constructor(private val repo: BookRepository) : ViewModel() {

    private val _books = MutableStateFlow<List<Book>>(emptyList())
    val books: StateFlow<List<Book>> = _books

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private var currentPage = 1
    private var endReached = false

    private var currentGenre = ""

    private var isSearchMode = false

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _isSearching = MutableStateFlow(false)
    val isSearching: StateFlow<Boolean> = _isSearching

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }


    init {
        viewModelScope.launch {
            _searchQuery
                .debounce(400)
                .distinctUntilChanged()
                .collectLatest { query ->
                    if (query.isNotBlank()) {
                        _isSearching.emit(true)
                        _isLoading.emit(true)
                        val result = repo.searchBooksInGenre(currentGenre, query)
                        _books.emit(result)
                        _isLoading.emit(false)
                    } else {
                        clearSearchAndReloadGenre(currentGenre)
                    }
                }
        }
    }


    fun loadBooks(genre: String) {
        viewModelScope.launch {
            _isLoading.value = true
//            val result = repo.getBooksByGenre(genre)
            val result = repo.getBooks()
            Log.d("BooksViewModel", "Loaded ${result.toString()} books")
            _books.value = result
            _isLoading.value = false
        }
    }

//

    fun loadBooksByGenre(genre: String, loadNextPage: Boolean = false) {
        if (_isLoading.value || endReached) return

        viewModelScope.launch {
            _isLoading.value = true

            // Set genre only once
            if (!loadNextPage) {
                currentGenre = genre
                currentPage = 1
                _books.value = emptyList()
                endReached = false
            }

            val result = repo.getBooksByGenre(currentGenre, currentPage)
            if (result.isNotEmpty()) {
                _books.value = _books.value + result
                currentPage++
            } else {
                endReached = true
            }

            _isLoading.value = false
        }
    }

    fun searchBooksInGenres(query: String) {
        if (query.isBlank()) return

        viewModelScope.launch {
            _isLoading.emit(true)
            val result = repo.searchBooksInGenre(currentGenre, query)
            _books.emit(result)
            _isLoading.emit(false)
        }
    }

    fun clearSearchAndReloadGenre(genre: String) {
        viewModelScope.launch {
            isSearchMode = false
            _searchQuery.value = ""
            _books.emit(emptyList())
            loadBooksByGenre(genre)
        }
    }

}
