package com.company.app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import com.company.app.presentation.util.MyApp
import com.company.app.util.ConnectivityManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var connectivityManager: ConnectivityManager


    override fun onStart() {
        super.onStart()
        connectivityManager.registerConnectionObserver(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        connectivityManager.unregisterConnectionObserver(this)
    }

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp(connectivityManager.isNetworkAvailable.value)
        }
    }
}
