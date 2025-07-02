package com.ashish.bookignitesol.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.packInts
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ashish.bookignitesol.Navigation.Screen
import com.ashish.bookignitesol.ui.theme.ThemeColorPrimary

/**
 * Created by Ashish Kr on 02,July,2025
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BookGenreScreen(navController: NavHostController){
    Scaffold {
        Column(Modifier.padding(20.dp)) {

            Spacer(modifier = Modifier.height(50.dp))

            Text("Gutenberg \nProject",
//                style = MaterialTheme.typography.titleLarge,
                color = ThemeColorPrimary,
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 45.sp
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text("A social cataloging website that allows you to freely search its database of books, annotations" +
                    "and reviews.",
//                style = MaterialTheme.typography.labelMedium,
                color = Color.Black,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(20.dp))


            Card(modifier = Modifier.padding(2.dp)
                .height(50.dp).clickable{
                    navController.navigate(Screen.BookListScreen.withArgs("FICTION"))

                }) {
                Row(modifier = Modifier.fillMaxWidth().padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {

                    Row(Modifier.fillMaxWidth(0.8f),
                        verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.AccountBox, "icon", tint = ThemeColorPrimary,
                            modifier = Modifier.size(35.dp))
                        Text("FICTION", modifier = Modifier.padding(start = 20.dp),
                            fontWeight = FontWeight.Bold)
                    }
                    Row {
                        Icon(Icons.Default.ArrowForward, "icon",
                            tint = ThemeColorPrimary,
                            modifier = Modifier.size(35.dp))
                    }

                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            Card(modifier = Modifier.padding(2.dp)
                .height(50.dp).clickable{
                    navController.navigate(Screen.BookListScreen.withArgs("DRAMA"))

                }) {
                Row(modifier = Modifier.fillMaxWidth().padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {

                    Row(Modifier.fillMaxWidth(0.8f),
                        verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.AccountBox, "icon", tint = ThemeColorPrimary,
                            modifier = Modifier.size(35.dp))
                        Text("DRAMA", modifier = Modifier.padding(start = 20.dp),
                            fontWeight = FontWeight.Bold)
                    }
                    Row {
                        Icon(Icons.Default.ArrowForward, "icon",
                            tint = ThemeColorPrimary,
                            modifier = Modifier.size(35.dp))
                    }

                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            Card(modifier = Modifier.padding(2.dp)
                .height(50.dp).clickable{
                    navController.navigate(Screen.BookListScreen.withArgs("HUMOR"))

                }) {
                Row(modifier = Modifier.fillMaxWidth().padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {

                    Row(Modifier.fillMaxWidth(0.8f),
                        verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.AccountBox, "icon", tint = ThemeColorPrimary,
                            modifier = Modifier.size(35.dp))
                        Text("HUMOR", modifier = Modifier.padding(start = 20.dp),
                            fontWeight = FontWeight.Bold)
                    }
                    Row {
                        Icon(Icons.Default.ArrowForward, "icon",
                            tint = ThemeColorPrimary,
                            modifier = Modifier.size(35.dp))
                    }

                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            Card(modifier = Modifier.padding(2.dp)
                .height(50.dp).clickable{
                    navController.navigate(Screen.BookListScreen.withArgs("POLITICS"))

                }) {
                Row(modifier = Modifier.fillMaxWidth().padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {

                    Row(Modifier.fillMaxWidth(0.8f),
                        verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.AccountBox, "icon", tint = ThemeColorPrimary,
                            modifier = Modifier.size(35.dp))
                        Text("POLITICS", modifier = Modifier.padding(start = 20.dp),
                            fontWeight = FontWeight.Bold)
                    }
                    Row {
                        Icon(Icons.Default.ArrowForward, "icon",
                            tint = ThemeColorPrimary,
                            modifier = Modifier.size(35.dp))
                    }

                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            Card(modifier = Modifier.padding(2.dp)
                .height(50.dp).clickable{
                    navController.navigate(Screen.BookListScreen.withArgs("PHILOSOPHY"))

                }) {
                Row(modifier = Modifier.fillMaxWidth().padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {

                    Row(Modifier.fillMaxWidth(0.8f),
                        verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.AccountBox, "icon", tint = ThemeColorPrimary,
                            modifier = Modifier.size(35.dp))
                        Text("PHILOSOPHY", modifier = Modifier.padding(start = 20.dp),
                            fontWeight = FontWeight.Bold)
                    }
                    Row {
                        Icon(Icons.Default.ArrowForward, "icon",
                            tint = ThemeColorPrimary,
                            modifier = Modifier.size(35.dp))
                    }

                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            Card(modifier = Modifier.padding(2.dp)
                .height(50.dp).clickable{
                    navController.navigate(Screen.BookListScreen.withArgs("HISTORY"))

                }) {
                Row(modifier = Modifier.fillMaxWidth().padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {

                    Row(Modifier.fillMaxWidth(0.8f),
                        verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.AccountBox, "icon", tint = ThemeColorPrimary,
                            modifier = Modifier.size(35.dp))
                        Text("HISTORY", modifier = Modifier.padding(start = 20.dp),
                            fontWeight = FontWeight.Bold)
                    }
                    Row {
                        Icon(Icons.Default.ArrowForward, "icon",
                            tint = ThemeColorPrimary,
                            modifier = Modifier.size(35.dp))
                    }

                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            Card(modifier = Modifier.padding(2.dp)
                .height(50.dp).clickable{
                    navController.navigate(Screen.BookListScreen.withArgs("ADVENTURE"))

                }) {
                Row(modifier = Modifier.fillMaxWidth().padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {

                    Row(Modifier.fillMaxWidth(0.8f),
                        verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.AccountBox, "icon", tint = ThemeColorPrimary,
                            modifier = Modifier.size(35.dp))
                        Text("ADVENTURE", modifier = Modifier.padding(start = 20.dp),
                            fontWeight = FontWeight.Bold)
                    }
                    Row {
                        Icon(Icons.Default.ArrowForward, "icon",
                            tint = ThemeColorPrimary,
                            modifier = Modifier.size(35.dp))
                    }

                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewBookGenreScreen(){
    BookGenreScreen(rememberNavController())

}