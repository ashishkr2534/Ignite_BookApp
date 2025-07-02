package com.ashish.bookignitesol

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.ashish.bookignitesol.Navigation.NavGraph
import com.ashish.bookignitesol.ui.theme.BookIgniteSolTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookIgniteSolTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Column {
//                        Greeting(
//                            name = "Android",
//                            modifier = Modifier.padding(innerPadding)
//                        )
//                        BooksListScreen("hn")
//                    }
//
//                }
                val navController = rememberNavController()

                NavGraph(navController)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BookIgniteSolTheme {
        Greeting("Android")
    }
}