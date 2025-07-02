package com.ashish.bookignitesol.Navigation


/**
 * Created by Ashish Kr on 03,May,2025
 */
//@Composable
//fun BottomBar(navController: NavController) {
//    val items = listOf(
//        BottomNavScreen.News,
//        BottomNavScreen.Profile,
//        BottomNavScreen.Settings
//    )
//    val currentBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentRoute = currentBackStackEntry?.destination?.route
//
//    NavigationBar {
//        items.forEach { screen ->
//            NavigationBarItem(
//                icon = { Icon(screen.icon, contentDescription = screen.title) },
//                label = { Text(screen.title) },
//                selected = currentRoute == screen.route,
//                onClick = {
//                    if (currentRoute != screen.route) {
//                        navController.navigate(screen.route) {
//                            popUpTo(navController.graph.startDestinationId) { saveState = true }
//                            launchSingleTop = true
//                            restoreState = true
//                        }
//                    }
//                }
//            )
//        }
//    }
//}
