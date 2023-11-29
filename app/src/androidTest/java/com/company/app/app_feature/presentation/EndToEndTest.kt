package com.company.app.app_feature.presentation

import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.company.app.di.AppModule
import com.company.app.di.LocalModule
import com.company.app.di.NetworkModule
import com.company.app.presentation.MainActivity
import com.company.app.theme.AppTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule

@HiltAndroidTest
@UninstallModules(AppModule::class, LocalModule::class, NetworkModule::class)
class EndToEndTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @ExperimentalAnimationApi
    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.activity.setContent {
            AppTheme(
                darkTheme = true,
            ) {

            }
        }
    }

}