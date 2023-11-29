package com.company.app.presentation.screen_first

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.company.app.presentation.screen_second.navigation.SecondScreenDestination
import com.company.app.theme.AppTheme
import com.company.app.theme.UIState

@ExperimentalAnimationApi
@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
fun FirstScreen(
    navController: NavController,
    viewModel: FirstScreenViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val loading = viewModel.loading.value
    val isConnected = viewModel.isConnected.value
    val snackbarHostState = remember { SnackbarHostState() }
    val dialogQueue = viewModel.dialogQueue

    AppTheme(
        darkTheme = true,
        dialogQueue = dialogQueue.queue.value,
        uiState = UIState(loading = loading, offline = isConnected)
    ) {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        navController.navigate(SecondScreenDestination.route)
                    },
                    modifier = Modifier.background(MaterialTheme.colorScheme.primary)
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Enter")
                }
            },
            snackbarHost = { SnackbarHost(snackbarHostState) }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                LazyColumn {
                    item {
                        Text(
                            text = "Get Data",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                                .clickable {
                                    viewModel.onEvent(FirstScreenEvent.GetData)
                                },
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                    items(state.data) {
                        Text(
                            text = it.title,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier
                                .padding(10.dp)
                                .clickable {
                                    viewModel.onEvent(FirstScreenEvent.DeleteData(it))
                                }
                        )
                    }

                }


            }
        }

    }

}