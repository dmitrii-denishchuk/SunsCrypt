package ru.iji.sunscrypt.presentation

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.iji.sunscrypt.presentation.screens.ScMainScreen
import ru.iji.sunscrypt.presentation.theme.SunsCryptTheme
import ru.iji.sunscrypt.presentation.viewmodels.ScViewModel

@AndroidEntryPoint
class ScActivity : ComponentActivity() {

    private val scViewModel: ScViewModel by viewModels()
    private var isActive = true

    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen().setKeepOnScreenCondition { isActive }
        enableEdgeToEdge(statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT))

        super.onCreate(savedInstanceState)

        setContent {

            val modifier = Modifier
            val uiState by scViewModel.uiState.collectAsStateWithLifecycle()

            SunsCryptTheme {
                Surface(modifier = modifier.fillMaxSize()) {
                    ScMainScreen(
                        modifier = modifier,
                        uiState = uiState,
                        uiEvent = scViewModel::onEvent
                    )
                }
            }
        }

        lifecycleScope.launch {
            scViewModel.intent.collect { intent ->
                intent?.let { startActivity(it) }
            }
        }

        isActive = false
    }
}