package com.ashish.bookignitesol.Screens

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.packInts
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ashish.bookignitesol.Navigation.Screen
import com.ashish.bookignitesol.ui.theme.ThemeColorPrimary
import com.ashish.bookignitesol.R
import com.ashish.bookignitesol.ui.theme.ThemeColorSecondary
import com.ashish.bookignitesol.ui.theme.montserrat_bold
import com.ashish.bookignitesol.ui.theme.montserrat_regular

/**
 * Created by Ashish Kr on 02,July,2025
 */

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BookGenreScreen(navController: NavHostController) {

    val genres = listOf(
        Genre("FICTION", R.drawable.fiction),
        Genre("DRAMA", R.drawable.drama),
        Genre("HUMOR", R.drawable.humour),
        Genre("POLITICS", R.drawable.politics),
        Genre("PHILOSOPHY", R.drawable.philosophy),
        Genre("HISTORY", R.drawable.history),
        Genre("ADVENTURE", R.drawable.adventure)
    )

    Scaffold {
        Column(Modifier.background(ThemeColorSecondary)
            .padding(20.dp)
            .fillMaxSize()) {

            Spacer(modifier = Modifier.height(50.dp))

            Box(
                modifier = Modifier.background(Transparent)
                    .fillMaxWidth()
                    .height(240.dp)
            ) {
//                Image(
//                    painter = painterResource(R.drawable.pattern),
//                    contentDescription = "Background",
//                    modifier = Modifier.fillMaxSize(),
//                    contentScale = ContentScale.Crop
//                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        "Gutenberg \nProject",
                        color = ThemeColorPrimary,
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 45.sp,
                        fontFamily = montserrat_bold

                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        "A social cataloging website that allows you to freely search its database of books, annotations and reviews.",
                        color = Color.Black, // assuming dark image
                        fontSize = 22.sp,
                        fontFamily = montserrat_regular
                    )
                }
            }


            Spacer(modifier = Modifier.height(20.dp))

            genres.forEach { genre ->
                GenreCard(genre = genre) {
                    navController.navigate(Screen.BookListScreen.withArgs(genre.name))
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}
data class Genre(
    val name: String,
    @DrawableRes val iconRes: Int
)


@Composable
fun GenreCard(genre: Genre, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(2.dp)
            .height(50.dp)
            .fillMaxWidth()
            .shadow(5.dp, shape = RoundedCornerShape(5.dp))
            .background(color = White, shape = RoundedCornerShape(5.dp))
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                Modifier.fillMaxWidth(0.8f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(genre.iconRes),
                    contentDescription = "${genre.name} icon",
                    modifier = Modifier.size(35.dp)
                )
                Text(
                    text = genre.name,
                    modifier = Modifier.padding(start = 20.dp),
                    fontWeight = FontWeight.Bold,
                    color = Black,
                    fontFamily = montserrat_bold
                )
            }

            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "arrow",
                tint = ThemeColorPrimary,
                modifier = Modifier.size(35.dp)
            )
        }
    }
}


@Preview
@Composable
fun PreviewBookGenreScreen(){
    BookGenreScreen(rememberNavController())

}