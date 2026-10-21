package com.gdgoau.securescreen.ui.body

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.gdgoau.securescreen.R

@Composable
fun ArtBody() {
    Image(
        painter = painterResource(R.drawable.sample_art),
        contentDescription = "Sample artwork",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxWidth().aspectRatio(3f / 2f).clip(MaterialTheme.shapes.extraLarge),
    )
    Text("Take a screenshot, record the screen, then open recent apps.", style = MaterialTheme.typography.bodyLarge)
}
