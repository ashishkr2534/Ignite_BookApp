package com.ashish.bookignitesol.Screens

/**
 * Created by Ashish Kr on 02,July,2025
 */

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.packInts
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ashish.bookignitesol.ui.theme.ThemeColorPrimary
import com.ashish.ignitebookapp.viewmodel.BooksViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@Composable
//fun BookList(navController: NavHostController, genre: String = "Fiction") {
//    val viewModel: BooksViewModel = hiltViewModel()
//    val books by viewModel.books.collectAsState()
//    val isLoading by viewModel.isLoading.collectAsState()
//
//    // Trigger loading once
//    LaunchedEffect(genre) {
//        viewModel.loadBooks(genre)
//    }
@Composable
fun BookList(navController: NavHostController, genre: String = "Fiction",
             viewModel: BooksViewModel  = hiltViewModel()){

    var selectedGenre by remember { mutableStateOf(genre) }
    var searchQuery by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current


    val books by viewModel.books.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadBooksByGenre(genre)

    }

    Scaffold(topBar ={
        CenterAlignedTopAppBar(
            title = {
                Row {
                    Text("${selectedGenre.toString()}".toString(),  fontSize = 22.sp,
                        color = ThemeColorPrimary, fontWeight = FontWeight.Bold)
                }

            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back",
                        tint = ThemeColorPrimary,
                        modifier = Modifier.size(35.dp))
                }
            }
        )
    } ) {
        Column(Modifier.padding(20.dp)) {

            Spacer(modifier = Modifier.height(60.dp))
            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                placeholder = { Text("Search books...") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon"
                    )
                },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { searchQuery = "" }) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Clear Search"
                            )
                        }
                    }
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {
                    // Perform search action
                    keyboardController?.hide()
                }),
                colors = TextFieldDefaults.colors(focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent)
            )

            if (isLoading) {
                CircularProgressIndicator()
            } else {
                LazyColumn {
                    items(books) { book ->
                        Text(text = book.title, fontWeight = FontWeight.Bold)
                        // You can build BookCard composable here for nicer UI
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }


        }

    }
}

@Preview
@Composable
fun PreviewBookListScreen(){
    BookList(rememberNavController())

}