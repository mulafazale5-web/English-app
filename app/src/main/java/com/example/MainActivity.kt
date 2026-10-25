package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.local.CoachDatabase
import com.example.data.repository.CoachRepository
import com.example.speech.OfflineSpeechController
import com.example.ui.CoachViewModel
import com.example.ui.EnglishCoachApp
import com.example.ui.theme.EnglishCoachTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = CoachDatabase.getInstance(applicationContext)
        val repository = CoachRepository(database.coachDao())

        setContent {
            val speechController = remember { OfflineSpeechController(applicationContext) }
            val coachViewModel: CoachViewModel = viewModel(
                factory = CoachViewModel.provideFactory(repository, speechController)
            )
            val uiState by coachViewModel.uiState.collectAsStateWithLifecycle()

            val systemDark = isSystemInDarkTheme()
            val useDarkTheme = when (uiState.settings.themeMode) {
                "DARK" -> true
                "LIGHT" -> false
                else -> systemDark
            }

            EnglishCoachTheme(
                darkTheme = useDarkTheme,
                textScale = uiState.settings.fontScaleFactor()
            ) {
                EnglishCoachApp(viewModel = coachViewModel)
            }
        }
    }
}
