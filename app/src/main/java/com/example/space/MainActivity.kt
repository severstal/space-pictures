package com.example.space

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.space.ui.SpaceHomeNavigation
import com.example.space.ui.theme.SpacePicturesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpacePicturesTheme {
                SpaceHomeNavigation()
            }
        }
    }
}

// todo translated explanation
// todo random pictures mode
// todo cache for apod's data (db)
// todo cache for pictures
// todo network error handler
