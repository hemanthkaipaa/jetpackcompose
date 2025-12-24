package com.kaipa.jetpackcompose.ilearn.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kaipa.jetpackcompose.ilearn.components.ButtonWrapper
import com.kaipa.jetpackcompose.ilearn.components.ColumnWrapper
import com.kaipa.jetpackcompose.ilearn.components.MovieRow
import com.kaipa.jetpackcompose.ilearn.model.getMovies
import com.kaipa.jetpackcompose.ilearn.screens.AppScreens
import com.kaipa.jetpackcompose.ilearn.ui.theme.Purple40
import com.kaipa.jetpackcompose.ilearn.ui.theme.Purple80
import kotlinx.coroutines.launch

@Composable
fun MovieHomeScreen(navController: NavController) {
    MainSurface(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainSurface(navController: NavController) {
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val movieList = getMovies()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
        topBar = {
            TopAppBar(modifier = Modifier.fillMaxWidth(), title = {
                Text(text = "Movies App")
            }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Purple80))

        },
        floatingActionButton = {
            ButtonWrapper(
                imageVector = Icons.Default.Add,
                size = 60.dp,
                isPaddingValues = false,
                buttonColor = Purple80,
                buttonTintColor = Purple40
            ) {
                scope.launch { snackBarHostState.showSnackbar(message = "Added Movie") }
            }

        },
        floatingActionButtonPosition = FabPosition.End,
    ) {
        ColumnWrapper(Modifier
            .fillMaxSize()
            .padding(it)) {
            LazyColumn() {
                items(movieList) {
                    MovieRow(it) {
                        navController.navigate(AppScreens.MovieHomeDetailScreen.name.plus("/${it.id}"))
                    }
                }
            }
        }
    }

}
@Preview
@Composable
fun preview(){
    val navController = rememberNavController()
    MainSurface(navController)
}