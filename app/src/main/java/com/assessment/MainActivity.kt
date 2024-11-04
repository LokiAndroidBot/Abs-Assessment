package com.assessment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.assessment.navigation.NavGraph
import com.assessment.ui.theme.AssessmentAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            AssessmentAppTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize(), // Make sure to use the modifier here
                    content = { paddingValues -> // Add paddingValues parameter
                        Surface(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues), // Apply padding to the Surface
                            color = Color.White
                        ) {
                            NavGraph(navController = navController)
                        }
                    })
            }
        }
    }
}



