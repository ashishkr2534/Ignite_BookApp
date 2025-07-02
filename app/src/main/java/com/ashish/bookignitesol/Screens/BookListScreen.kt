package com.ashish.ignitebookapp.Screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import com.ashish.ignitebookapp.Util.getBookUrl
import com.ashish.ignitebookapp.viewmodel.BooksViewModel
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import com.ashish.ignitebookapp.models.Book
import javax.inject.Inject

//import com.google.android.mediahome.books.BookItem

/**
 * Created by Ashish Kr on 02,July,2025
 */


@Composable
fun BooksListScreen(genre: String, viewModel: BooksViewModel = hiltViewModel()) {
    val books by viewModel.books.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(genre) {
//        viewModel.loadBooks(genre)
        viewModel.loadBooksSearch(genre)
//        loadBooksSearch.
    }

    val context = LocalContext.current

    LazyColumn(modifier = Modifier.fillMaxSize().padding(8.dp)) {
        items(books) { book ->

//            Card {
//                Column {
//                    Text(book.authors.toString())
//                    Text(book.title.toString())
//                }
//            }
            BookItem(book) {
                val url = getBookUrl(book.formats)
                if (url != null) {
                    val intent = Intent(Intent.ACTION_VIEW, url.toUri())
                    startActivity(context, intent, null)
                } else {
                    Toast.makeText(
                        context,
                        "No viewable version available",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        item {
            if (isLoading) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun BookItem(book: Book, onClick: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp).clickable { onClick() }) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(book.title, style = MaterialTheme.typography.titleMedium)
            Text(book.authors.joinToString { it.name }, style = MaterialTheme.typography.bodySmall)
        }
    }
}
