package com.ashish.bookignitesol.Screens

/**
 * Created by Ashish Kr on 02,July,2025
 */

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.packInts
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.ashish.bookignitesol.ui.theme.ThemeColorPrimary
import com.ashish.bookignitesol.ui.theme.ThemeColorSecondary
import com.ashish.ignitebookapp.models.Book
import com.ashish.ignitebookapp.viewmodel.BooksViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

@Composable
fun BookList(
    navController: NavHostController,
    genre: String = "Fiction",
    viewModel: BooksViewModel = hiltViewModel()
) {
    var selectedGenre by remember { mutableStateOf(genre) }
//    var searchQuery by remember { mutableStateOf("") }

    //search query replaced by viewmodel to sarch frequently for text value change
    val searchQuery by viewModel.searchQuery.collectAsState()

    val keyboardController = LocalSoftwareKeyboardController.current

    val books by viewModel.books.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val gridState = rememberLazyGridState()

    LaunchedEffect(Unit) {
        viewModel.loadBooksByGenre(genre)
    }

    // Infinite scroll for genre list
    LaunchedEffect(gridState) {
        snapshotFlow { gridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collect { lastVisibleIndex ->
                if (lastVisibleIndex == books.size - 1 && !isLoading && searchQuery.isBlank()) {
                    viewModel.loadBooksByGenre(genre, loadNextPage = true)
                }
            }
    }

    Scaffold(containerColor = White,topBar = {

        TopAppBar(
            title = {
                Text(
                    text = selectedGenre.uppercase(),
                    fontSize = 22.sp,
                    color = ThemeColorPrimary,
                    fontWeight = FontWeight.Bold
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = ThemeColorPrimary,
                        modifier = Modifier.size(35.dp)
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = White)
        )

    }) { paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 10.dp, vertical = 16.dp)) {

            TextField(
                value = searchQuery,
//                onValueChange = {
//                    searchQuery = it
//                    if (searchQuery.isBlank()) {
//                        viewModel.clearSearchAndReloadGenre(genre)
//                    }
//                },
                onValueChange = { viewModel.onSearchQueryChanged(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 6.dp),
                placeholder = { Text("Search") },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
                },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = {
//                            searchQuery = ""
                            viewModel.clearSearchAndReloadGenre(genre)
                        }) {
                            Icon(Icons.Default.Clear, contentDescription = "Clear Search")
                        }
                    }
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {
                    keyboardController?.hide()
                    viewModel.searchBooksInGenres(searchQuery)
                }),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = ThemeColorSecondary,
                    unfocusedContainerColor = ThemeColorSecondary,
                    disabledContainerColor = ThemeColorSecondary
                ),
                shape = RoundedCornerShape(5.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .wrapContentWidth(Alignment.CenterHorizontally),
                    color = ThemeColorPrimary
                )
            } else {
                LazyVerticalGrid(
                    state = gridState,
                    columns = GridCells.Fixed(3),
                    contentPadding = PaddingValues(8.dp),
                    modifier = Modifier.fillMaxSize().background(ThemeColorSecondary)
                ) {
                    items(books) { book ->
                        BookCard(book = book)
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

@Composable
fun BookCard(book: Book, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .width(140.dp)
            .padding(8.dp)
            .clickable {
                val preferredMimeTypes = listOf(
                    "text/html",
                    "application/pdf",
                    "text/plain"
                )

                val viewableUrl = preferredMimeTypes.firstNotNullOfOrNull { mime ->
                    val url = book.formats[mime]
                    if (url != null && !url.endsWith(".zip", ignoreCase = true)) url else null
                }

                if (viewableUrl != null) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(viewableUrl))
                    context.startActivity(intent)
                } else {
                    AlertDialog.Builder(context)
                        .setTitle("Error")
                        .setMessage("No viewable version available")
                        .setPositiveButton("OK", null)
                        .show()
                }
            }
    ) {
        if (book.imageUrl != null) {
            AsyncImage(
                model = book.imageUrl,
                contentDescription = book.title,
                modifier = Modifier
                    .height(150.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = book.title.uppercase(),
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.SemiBold
            ),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = book.authors.firstOrNull()?.name ?: "Unknown",
            style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}
