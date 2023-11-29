package com.company.app.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ConnectivityMonitor() {

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            "No network connection",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(8.dp),
            style = MaterialTheme.typography.headlineLarge,
            color = Color.Red
        )
    }

}