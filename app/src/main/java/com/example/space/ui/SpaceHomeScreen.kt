package com.example.space.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.space.state.SpaceViewModel
import com.example.space.ui.component.SpaceCard
import com.example.space.ui.component.SpaceAppBar

@Composable
fun SpaceHomeScreen(
    spaceViewModel: SpaceViewModel,
    onDescriptionPressed: (description: String?) -> Unit,
    onImagePressed: (url: String?) -> Unit
) {

    val uiState by spaceViewModel.uiState.collectAsState()
    val listState = rememberLazyListState()

    Column {
        SpaceAppBar(onIconPressed = { })
        Spacer(Modifier.height(5.dp))

        LazyColumn(
            state = listState,
        ) {

            this.items(
                items = uiState.pictures,
                key = { it.date }
            ) { picture ->
                picture.url?.let {
                    SpaceCard(
                        picture,
                        onDescriptionPressed = { description -> onDescriptionPressed(description) },
                        onImagePressed = { url -> onImagePressed(url) }
                    )
                }
            }
        }
    }

    if (listState.isScrolledToTheEnd()) {
        spaceViewModel.requestPageOfPictures()
    }
}


fun LazyListState.isScrolledToTheEnd() =
    layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1
