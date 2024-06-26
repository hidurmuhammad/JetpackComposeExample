package com.example.jetpackcompose_example.ui.composeui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.jetpackcompose_example.model.Receipes
import com.example.jetpackcompose_example.utils.UiState
import com.example.jetpackcompose_example.viemodel.ReceipeViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailScreen(navController: NavController, mainViewModel: ReceipeViewModel, id: Int?) {
    val scrollstate = rememberScrollState()
    Scaffold(
        topBar = {
            CustomToolbarScreen(navController = navController, title = "Details", true)
        }
    )
    { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(scrollstate)
                .padding(2.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //add your code
            LaunchedEffect(key1 = Unit) {
                getReceipesDetails(mainViewModel, id)
            }
            val state = mainViewModel.uiStateReceipeDetail.collectAsState()
            when (state.value) {
                is UiState.Success -> {
                    ProgressLoader(isLoading = false)
                    (state.value as UiState.Success<Receipes.Recipe>).data?.let {
                        RecipeDetailView(recipe = it)
                    }
                }

                is UiState.Loading -> {
                    ProgressLoader(isLoading = true)
                }

                is UiState.Error -> {
                    ProgressLoader(isLoading = false)
                    //Handle Error
                    SimpleAlertDialog(message =  ((state.value as UiState.Error<Receipes>).message))
                }
            }
        }
    }

}

private fun getReceipesDetails(mainViewModel: ReceipeViewModel, id: Int?) {
    // Call the function to fetch recipes
    mainViewModel.getReceipeDetail(id)
}


@Composable
fun RecipeDetailView(recipe: Receipes.Recipe) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(recipe.image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = recipe.name ?: "",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Ingredients:",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Gray
        )
        Column(
            modifier = Modifier.padding(start = 16.dp)
        ) {
            recipe.ingredients?.forEach { ingredient ->
                Text(
                    text = "• $ingredient",
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Instructions:",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Gray
        )
        Column(
            modifier = Modifier.padding(start = 16.dp)
        ) {
            recipe.instructions?.forEachIndexed { index, instruction ->
                Text(
                    text = "${index + 1}. $instruction",
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
        }
    }
}