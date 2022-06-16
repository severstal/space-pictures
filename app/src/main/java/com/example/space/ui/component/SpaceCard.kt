package com.example.space.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.space.domain.SpacePictureDto
import com.example.space.ui.theme.SpaceBackgroundColor

@Composable
fun SpaceCard(
    picture: SpacePictureDto,
    onDescriptionPressed: (description: String?) -> Unit,
    onImagePressed: (url: String?) -> Unit
) {
    Card(
        modifier = Modifier.padding(5.dp)
            .clip(RoundedCornerShape(10.dp))
    ) {
        Row(modifier = Modifier
            .background(color = SpaceBackgroundColor)
            .clickable { onDescriptionPressed(picture.explanation) }
        ) {
            AsyncImage(
                model = picture.url,
                contentDescription = null,
                placeholder = ColorPainter(MaterialTheme.colors.surface),
                modifier = Modifier
                    .size(200.dp)
                    .padding(5.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable { onImagePressed(picture.hdUrl ?: picture.url) },
                contentScale = ContentScale.Crop
            )

            val cardTitle = picture.title ?: "no title"
            val cardDate = " (${picture.date})"
            Text(
                modifier = Modifier.align(Top)
                    .padding(5.dp)
                    .fillMaxWidth(),
                text = cardTitle + cardDate
            )
        }
    }
}
