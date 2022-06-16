package com.example.space.ui.component

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.space.R
import com.example.space.ui.theme.SpaceBackgroundColor

@Composable
fun SpaceAppBar(modifier: Modifier = Modifier, onIconPressed: () -> Unit) {

    TopAppBar(
        title = {
            Text(text = stringResource(R.string.app_name))
        },
        navigationIcon = {
            IconButton(onClick = { onIconPressed() }) {
                Icon(
                    painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = null,
                    tint = MaterialTheme.colors.onBackground,
                )
            }
        },
        backgroundColor = SpaceBackgroundColor,
        modifier = modifier,
    )

}
