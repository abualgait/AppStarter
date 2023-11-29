package com.company.app.presentation.util

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.company.app.presentation.screen_first.navigation.FirstScreenDestination
import com.company.app.presentation.screen_first.navigation.firstScreenGraph
import com.company.app.presentation.screen_second.navigation.secondScreenGraph
import com.company.app.theme.AppTheme

@Composable
fun MyApp(isNetworkAvailable: Boolean) {
    AppTheme(
        darkTheme = true,
    ) {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = FirstScreenDestination.route
            ) {
                firstScreenGraph(
                    navController = navController
                )

                secondScreenGraph(
                    navController = navController
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppTheme {
        MyApp(true)
    }
}