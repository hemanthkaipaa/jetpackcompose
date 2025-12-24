package com.kaipa.jetpackcompose.ilearn

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontVariation.weight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kaipa.jetpackcompose.ilearn.components.ButtonWrapper
import com.kaipa.jetpackcompose.ilearn.components.CardWrapper
import com.kaipa.jetpackcompose.ilearn.components.ColumnWrapper
import com.kaipa.jetpackcompose.ilearn.components.RowWrapper
import com.kaipa.jetpackcompose.ilearn.navigation.AppNavigation
import com.kaipa.jetpackcompose.ilearn.ui.theme.MyApplicationTheme
import com.kaipa.jetpackcompose.ilearn.ui.theme.Purple40
import com.kaipa.jetpackcompose.ilearn.ui.theme.Purple80
import kotlinx.coroutines.launch

class MoviesActivity : ComponentActivity() {

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme() {
                AppNavigation()
            }
        }
    }

    @Preview
    @Composable
    fun Preview(){
        AppNavigation()
    }

}