package com.kaipa.jetpackcompose.ilearn.screens.detail

import android.R.attr.contentDescription
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.kaipa.jetpackcompose.ilearn.components.AddRowSpace
import com.kaipa.jetpackcompose.ilearn.components.AddSpace
import com.kaipa.jetpackcompose.ilearn.components.CardWrapper
import com.kaipa.jetpackcompose.ilearn.components.ColumnWrapper
import com.kaipa.jetpackcompose.ilearn.components.MovieRow
import com.kaipa.jetpackcompose.ilearn.components.RowWrapper
import com.kaipa.jetpackcompose.ilearn.model.getMovies
import com.kaipa.jetpackcompose.ilearn.ui.theme.Purple80

@Composable
fun MovieDetailScreen(navController: NavController, movie: String?) {
    MainSurface(navController, movie)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainSurface(navController: NavController, movie: String?) {
    val movieObject = getMovies().find { it.id == movie }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    RowWrapper(arrangement = Arrangement.Start) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "pop backstack",
                            Modifier.clickable {
                                navController.popBackStack()
                            })
                        AddRowSpace()
                        Text(text = "Movie Details")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Purple80)
            )
        },

        ) {

        ColumnWrapper(Modifier
            .fillMaxSize()
            .padding(it), orientation = Arrangement.Center, alignment = Alignment.Start) {
            MovieRow(movieObject!!) {
                navController.popBackStack()
            }
            AddSpace(20.dp, height = 20.dp)
            Text(text = "Posters", Modifier.padding(20.dp), textAlign = TextAlign.Start)
            LazyRow() {
                items(movieObject.images) {
                    CardWrapper(cardHeight = 300.dp) {
                        AsyncImage(
                            modifier = Modifier.padding(4.dp).fillMaxSize(),
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(it)
                                .crossfade(200)
                                .crossfade(true)
                                .size(300)
                                .build(),
                            contentScale = Crop,
                            contentDescription = "Posters"
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    val navController = rememberNavController()
    MainSurface(navController, "Avengers")
}