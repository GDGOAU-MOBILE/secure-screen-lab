package com.gdgoau.securescreen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gdgoau.securescreen.R
import com.gdgoau.securescreen.ui.theme.GoogleBlue
import com.gdgoau.securescreen.ui.theme.GoogleGreen
import com.gdgoau.securescreen.ui.theme.GoogleRed

private val GoogleYellow = Color(0xFFF9AB00)

@Composable
fun SplashScreen() {
    Box(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.align(Alignment.Center).size(160.dp).clip(CircleShape).background(GoogleBlue),
        ) {
            Icon(painterResource(R.drawable.ic_shield_lock), contentDescription = null, tint = Color.White, modifier = Modifier.size(88.dp))
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.align(Alignment.BottomCenter).navigationBarsPadding().padding(bottom = 32.dp),
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                listOf(GoogleBlue, GoogleRed, GoogleYellow, GoogleGreen).forEach {
                    Box(Modifier.size(8.dp).clip(CircleShape).background(it))
                }
            }
            Text("GDG OAU", style = MaterialTheme.typography.titleMedium, letterSpacing = 4.sp)
        }
    }
}
