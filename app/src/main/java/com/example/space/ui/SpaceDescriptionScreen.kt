package com.example.space.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.space.ui.component.SpaceAppBar
import com.example.space.ui.theme.SpaceBackgroundColor

@Composable
fun SpaceDescriptionScreen(
    description: String?,
    onBackPressed: () -> Unit,
) {

    Column {
        SpaceAppBar(onIconPressed = { onBackPressed() })

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = SpaceBackgroundColor),
            contentAlignment = Alignment.TopStart
        ) {
            Text(
                text = "    $description" ?: "    no description",
                modifier = Modifier
                    .padding(5.dp)
            )
        }
    }

}