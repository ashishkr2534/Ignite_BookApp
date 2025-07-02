package com.ashish.bookignitesol.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ashish.bookignitesol.Screens.BookGenreScreen
import com.ashish.bookignitesol.Screens.BookList
import com.ashish.ignitebookapp.Screens.BooksListScreen


/**
 * Created by Ashish Kr on 20,May,2025
 */
@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "bookGenre_screen") {
        composable(Screen.BookGenreScreen.route) {
            BookGenreScreen(navController)
        }
        composable(Screen.BookListScreen.route) {
            BookList(navController)
        }

//        composable(Screen.WordPuzzleScreen.route){
//            WordPuzzleScreen(navController)
//        }
        composable(
            route = Screen.BookListScreen.route + "/{genre}",
            arguments = listOf(
                navArgument("genre") {
                    type = NavType.StringType
                    defaultValue = "1"
                    nullable = true
                }
            )
        ) { entry ->
            entry.arguments?.getString("genre")
                ?.let { BookList(navController,genre = it) }
        }


    }
}
