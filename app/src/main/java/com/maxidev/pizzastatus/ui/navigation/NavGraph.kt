package com.maxidev.pizzastatus.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.maxidev.pizzastatus.ui.presentation.PizzaScreen
import com.maxidev.pizzastatus.ui.presentation.components.PizzaCodeScreen

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: Destinations = Destinations.MainScreen
) {
    Scaffold(
        bottomBar = {
            NavigationBar(
                modifier = modifier,
                tonalElevation = NavigationBarDefaults.Elevation
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                val items = listOf(
                    Destinations.MainScreen,
                    Destinations.CodeListScreen
                )

                items.forEach { screen ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any {
                            it.route == screen.route
                        } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = screen.icon,
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(text = stringResource(id = screen.resourceId))
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            modifier = modifier
                .padding(paddingValues),
            navController = navController,
            startDestination = startDestination.route
        ) {
            composable(route = startDestination.route) {
                PizzaScreen()
            }
            composable(route = Destinations.CodeListScreen.route) {
                PizzaCodeScreen()
            }
        }
    }
}