package com.example.space.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun SpaceHomeNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            SpaceHomeScreen(
                hiltViewModel(),
                onDescriptionPressed = { description -> navController.navigate("description?text=$description") },
                onImagePressed = { url -> navController.navigate("image?url=$url") }
            )
        }

        composable(
            "description?text={text}",
            arguments = listOf(navArgument("text") { nullable = true })
        ) { backStackEntry ->
            SpaceDescriptionScreen(
                description = backStackEntry.arguments?.getString("text"),
                onBackPressed = { navController.popBackStack() }
            )
        }

        composable(
            "image?url={url}",
            arguments = listOf(navArgument("url") { nullable = true })
        ) { backStackEntry ->
            SpaceImageScreen(
                url = backStackEntry.arguments?.getString("url"),
                onBackPressed = { navController.popBackStack() }
            )
        }
    }

}