package com.example.jetpackcompose_example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.compose.runtime.Composable
import com.example.jetpackcompose_example.ui.composeui.RecipeDetailScreen
import com.example.jetpackcompose_example.ui.composeui.RecipesScreen
import com.example.jetpackcompose_example.ui.theme.JetpackCompose_ExampleTheme
import com.example.jetpackcompose_example.utils.Routes
import com.example.jetpackcompose_example.viemodel.ReceipeViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            mainContent()
        }
    }

    @Composable
    fun mainContent(){
        val mainViewModel : ReceipeViewModel = koinViewModel()
        JetpackCompose_ExampleTheme {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Routes.LIST_SCREEN) {
                composable(Routes.LIST_SCREEN) {
                    RecipesScreen(navigation= navController, mainViewModel)
                }
                composable(
                    Routes.DETAIL_SCREEN,arguments= listOf(navArgument("idValue"){
                        type = NavType.IntType
                    })
                ) {backStackEntry->
                    RecipeDetailScreen(navController,mainViewModel, backStackEntry.arguments?.getInt(Routes.Values.IDVALUE,0))
                }
            }
        }
    }
}
