package com.ashish.bookignitesol.Navigation

import androidx.annotation.StringRes
import com.ashish.bookignitesol.R

/**
 * Created by Ashish Kr on 02,May,2025
 */
const val RootRoute ="root"

sealed class Screen (val route:String,@StringRes val resourceId:Int){

//    object SplashScreen : Screen("splash_screen", R.string.splash_screen)
    object BookGenreScreen : Screen("bookGenre_screen", R.string.bookGenre_screen)
    object BookListScreen : Screen("bookList_screen", R.string.bookList_screen)



    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg -> append("/$arg") }
        }
    }

}