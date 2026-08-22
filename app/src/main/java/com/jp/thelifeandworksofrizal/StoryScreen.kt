package com.jp.thelifeandworksofrizal

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.jp.thelifeandworksofrizal.ui.theme.*

// Reference Color Palette
private val DarkBg = Color(0xFF0A0C0A)
private val GoldAccent = Color(0xFFD4AF37)
private val CreamText = Color(0xFFFDFBF7)
private val DarkGreenGlow = Color(0xFF143014)

@Composable
fun StoryScreen(book: RizalBook, onChapterSelected: (Int) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBg)
    ) {
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

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            contentPadding = PaddingValues(top = 24.dp, bottom = 48.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Book header (Museum Placard style)
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = book.title.uppercase(),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Black,
                        color = CreamText,
                        fontFamily = FontFamily.Serif,
                        textAlign = TextAlign.Center,
                        letterSpacing = 2.sp,
                        lineHeight = 40.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Explore Rizal's life through major chapters covering his childhood, education, reform work, writings, exile, and legacy.",
                        fontSize = 14.sp,
                        color = CreamText.copy(alpha = 0.8f),
                        lineHeight = 22.sp,
                        fontFamily = FontFamily.Serif,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    Divider(
                        color = GoldAccent.copy(alpha = 0.3f),
                        thickness = 1.dp,
                        modifier = Modifier.fillMaxWidth(0.5f)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            // Chapters List
            itemsIndexed(book.chapters) { index, chapter ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, GoldAccent.copy(alpha = 0.3f), RoundedCornerShape(16.dp))
                        .clickable { onChapterSelected(index) },
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color(0xFF1A1A1A).copy(alpha = 0.8f),
                                        DarkBg.copy(alpha = 0.9f)
                                    )
                                )
                            )
                            .padding(24.dp)
                    ) {
                        // Added a subtle chapter number label for extra polish
                        Text(
                            text = "CHAPTER ${index + 1}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = GoldAccent,
                            fontFamily = FontFamily.SansSerif,
                            letterSpacing = 2.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = chapter.title,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = CreamText,
                            fontFamily = FontFamily.Serif,
                            lineHeight = 30.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = chapter.subtitle,
                            fontSize = 14.sp,
                            color = CreamText.copy(alpha = 0.6f),
                            fontFamily = FontFamily.Serif,
                            lineHeight = 20.sp
                        )
                    }
                }
            }
        }
    }
}