package com.example.kartikee.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessibilityNew
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalLibrary
import androidx.compose.material.icons.filled.SupervisorAccount
import androidx.compose.ui.graphics.vector.ImageVector

 sealed class BottomNavItem(val route: String, val title: String, val icon: ImageVector) {
    object Home : BottomNavItem("home", "Home", Icons.Default.Home)
    object Library : BottomNavItem("library", "Library", Icons.Default.LocalLibrary)
    object Exercise : BottomNavItem("exercise", "Exercise", Icons.Default.AccessibilityNew)
    object Parental : BottomNavItem("parental", "Parental", Icons.Default.SupervisorAccount)
}

