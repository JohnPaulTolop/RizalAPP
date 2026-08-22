package com.jp.thelifeandworksofrizal

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Explore
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jp.thelifeandworksofrizal.R
import com.jp.thelifeandworksofrizal.Screen

// Reference Color Palette
private val DarkBg = Color(0xFF0A0C0A)
private val GoldAccent = Color(0xFFD4AF37)
private val CreamText = Color(0xFFFDFBF7)
val DarkOrangeGlow = Color(0xFF3D1C00) // Deep, warm burnt orange

@Composable
fun GetStartedScreen(navController: androidx.navigation.NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBg)
    ) {
        // 1. Faded Background Image
        Image(
            painter = painterResource(id = R.drawable.splash_background),
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            alpha = 0.4f,
            modifier = Modifier.fillMaxSize()
        )

        // 2. Vignette & Glow Overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            DarkOrangeGlow.copy(alpha = 0.4f), // Softer warm orange glow in the center
                            DarkBg.copy(alpha = 0.85f),        // Smooth transition to dark
                            DarkBg                             // Fully solid dark border at the extremes
                        ),
                        radius = 1800f // Significantly increased radius to eliminate harsh edges
                    )
                )
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            // Arch Image Frame
            val archShape = RoundedCornerShape(topStart = 150.dp, topEnd = 150.dp, bottomStart = 0.dp, bottomEnd = 0.dp)

            Box(
                contentAlignment = Alignment.TopCenter,
                modifier = Modifier.padding(bottom = 40.dp)
            ) {
                // Gold Outline Offset
                Box(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .width(220.dp)
                        .height(280.dp)
                        .border(1.dp, GoldAccent.copy(alpha = 0.6f), archShape)
                )

                // Clipped Header Image
                Image(
                    painter = painterResource(id = R.drawable.pixel_art_rizal),
                    contentDescription = "Jose Rizal",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(top = 32.dp)
                        .width(200.dp)
                        .height(260.dp)
                        .clip(archShape)
                        .background(Color.DarkGray)
                )
            }

            // Greeting
            Text(
                text = "Buen día!",
                fontSize = 16.sp,
                color = GoldAccent,
                fontFamily = FontFamily.Serif,
                letterSpacing = 2.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Grand Title
            Text(
                text = "JOSÉ\nRIZAL",
                fontSize = 56.sp,
                fontWeight = FontWeight.Black,
                color = CreamText,
                fontFamily = FontFamily.Serif,
                lineHeight = 56.sp,
                textAlign = TextAlign.Center,
                letterSpacing = 6.sp
            )

            Spacer(modifier = Modifier.height(64.dp))

            // Metallic Button
            Button(
                onClick = { navController.navigate(Screen.Login.route) },
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(56.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0xFFB8860B),
                                    GoldAccent,
                                    Color(0xFFB8860B)
                                )
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "GET STARTED",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = DarkBg,
                            fontFamily = FontFamily.SansSerif,
                            letterSpacing = 2.sp
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Icon(
                            imageVector = Icons.Rounded.Explore,
                            contentDescription = null,
                            tint = DarkBg,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}