package com.example.kartikee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.camera.camera2.Camera2Config
import androidx.camera.core.CameraXConfig
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.kartikee.navigation.BottomNavItem
import com.example.kartikee.feature.exercise.ExerciseScreen
import com.example.kartikee.feature.home.HomeScreen
import com.example.kartikee.feature.library.LibraryScreen
import com.example.kartikee.feature.parental.ParentalScreen
import com.example.kartikee.ui.theme.KartikeeTheme

class MainActivity : ComponentActivity(), CameraXConfig.Provider {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KartikeeTheme {
                MainScreen()
            }
        }
    }

    override fun getCameraXConfig(): CameraXConfig {
        return Camera2Config.defaultConfig()
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Library,
        BottomNavItem.Exercise,
        BottomNavItem.Parental
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEach { item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        label = { Text(item.title) },
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Home.route) { HomeScreen() }
            composable(BottomNavItem.Library.route) { LibraryScreen() }
            composable(BottomNavItem.Exercise.route) { ExerciseScreen() }
            composable(BottomNavItem.Parental.route) { ParentalScreen() }
        }
    }
}