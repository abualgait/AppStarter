package com.company.app.theme

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.company.app.presentation.components.CircularIndeterminateProgressBar
import com.company.app.presentation.components.ConnectivityMonitor
import com.company.app.presentation.components.GenericDialog
import com.company.app.presentation.components.GenericDialogInfo
import java.util.Queue

private val lightThemeColors = lightColorScheme(
    primary = AppColor,
    primaryContainer = Blue400,
    onPrimary = Black2,
    secondary = Color.White,
    secondaryContainer = Teal300,
    error = RedErrorDark,
    onError = RedErrorLight,
    background = Grey1,
    onBackground = Color.Black,
    surface = Color.White
)

private val darkThemeColors = darkColorScheme(
    primary = AppColor,
    onPrimary = Color.White,
    secondary = Black1,
    onSecondary = Color.White,
    error = RedErrorLight,
    background = Color.Black,
    onBackground = Color.White,
    surface = Black1,
    onSurface = Color.White
)

data class UIState(
    val loading: Boolean = false,
    val offline: Boolean = false,
    val error: Boolean = false,
    val empty: Boolean = false
)

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    uiState: UIState = UIState(),
    dialogQueue: Queue<GenericDialogInfo>? = null,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> darkThemeColors
        else -> lightThemeColors
    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = if (!darkTheme) Grey1 else Color.Black)
        ) {
            Column {
                if (uiState == UIState(offline = true)) {
                    ConnectivityMonitor()
                }
                content()
            }
            if (uiState == UIState(loading = true)) {
                CircularIndeterminateProgressBar()
            }

            if (uiState == UIState(error = true)) {
                ProcessDialogQueue(dialogQueue = dialogQueue)
            }

        }
    }
}

@Composable
fun ProcessDialogQueue(
    dialogQueue: Queue<GenericDialogInfo>?,
) {
    dialogQueue?.peek()?.let { dialogInfo ->
        GenericDialog(
            onDismiss = dialogInfo.onDismiss,
            title = dialogInfo.title,
            description = dialogInfo.description,
            positiveAction = dialogInfo.positiveAction,
            negativeAction = dialogInfo.negativeAction
        )
    }
}