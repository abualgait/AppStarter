package com.company.app.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


/**
 * Center a circular indeterminate progress bar.
 */

@SuppressLint("UnusedBoxWithConstraintsScope")
@ExperimentalComposeUiApi
@Composable
fun CircularIndeterminateProgressBar() {

        BoxWithConstraints(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize(),
        ) {

            CircularProgressIndicator(
                modifier = Modifier.padding(8.dp),
                color = MaterialTheme.colorScheme.primary,
            )
        }


}