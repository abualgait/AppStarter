package com.company.app.presentation.screen_second.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.company.app.navigation.AppNavigationDestination
import com.company.app.presentation.screen_second.SecondScreen

object SecondScreenDestination : AppNavigationDestination {
    override val route = "screen_second_route"
    override val destination = "screen_second_destination"
}

fun NavGraphBuilder.secondScreenGraph(navController: NavController) {
    composable(
        route = SecondScreenDestination.route +
                "?entityId={entityId}",
        arguments = listOf(
            navArgument(
                name = "entityId"
            ) {
                type = NavType.IntType
                defaultValue = -1
            }
        )
    ) {
        SecondScreen(
            navController = navController,
        )
    }
}
