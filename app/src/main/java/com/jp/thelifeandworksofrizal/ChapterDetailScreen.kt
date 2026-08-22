package com.jp.thelifeandworksofrizal

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
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
import com.jp.thelifeandworksofrizal.Chapter

private val DarkBg = Color(0xFF0A0C0A)
private val GoldAccent = Color(0xFFD4AF37)
private val CreamText = Color(0xFFFDFBF7)
private val DarkGreenGlow = Color(0xFF143014)

@Composable
fun ChapterDetailScreen(chapter: Chapter) {
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
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))

                val archShape = RoundedCornerShape(topStart = 150.dp, topEnd = 150.dp, bottomStart = 0.dp, bottomEnd = 0.dp)

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(380.dp),
                    contentAlignment = Alignment.TopCenter
                ) {
                    // Gold Outline Offset
                    Box(
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .width(220.dp)
                            .height(280.dp)
                            .border(1.dp, GoldAccent, archShape)
                    )

                    // Clipped Header Image with Fade
                    Box(
                        modifier = Modifier
                            .padding(top = 32.dp)
                            .width(200.dp)
                            .height(260.dp)
                            .clip(archShape)
                            .background(Color.DarkGray)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.pixel_art_rizal),
                            contentDescription = chapter.title,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )

                        // Black Gradient Fade (Scrim)
                        // This Box creates a black fade from the middle to the bottom of the image
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Transparent,
                                            Color.Black.copy(alpha = 0.6f), // Subtle dark start
                                            Color.Black.copy(alpha = 1f)  // Darker at the very bottom
                                        ),
                                        startY = 300f // Starts the fade approximately halfway down the image
                                    )
                                )
                        )
                    }

                    // Overlapping Display Title
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            // Adjusting the offset moves the text down
                            // At 40.dp, it should cover roughly the bottom 30% of the arch area
                            .offset(y = (40).dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = chapter.title.uppercase(),
                            color = CreamText,
                            fontSize = 42.sp,
                            fontWeight = FontWeight.Black,
                            fontFamily = FontFamily.Serif,
                            letterSpacing = 2.sp,
                            textAlign = TextAlign.Center,
                            lineHeight = 46.sp, // Tighter line height for stacked titles
                            modifier = Modifier.padding(horizontal = 24.dp)
                        )
                    }
                }
                // Added extra spacer to account for the downward text offset
                Spacer(modifier = Modifier.height(64.dp))
            }

            item {
                // Chapter Subtitle
                Text(
                    text = chapter.subtitle,
                    color = GoldAccent,
                    fontSize = 18.sp,
                    fontFamily = FontFamily.Serif
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Chapter Content Paragraphs
            val paragraphs = chapter.content.split("\n\n")
            items(paragraphs) { paragraph ->
                Text(
                    text = paragraph,
                    fontSize = 15.sp,
                    color = CreamText,
                    lineHeight = 24.sp,
                    fontFamily = FontFamily.Serif,
                    textAlign = TextAlign.Justify
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}