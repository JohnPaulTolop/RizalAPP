package com.jp.thelifeandworksofrizal

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun QuizScreen(
    quiz: ChapterQuiz,
    onBackToChapters: () -> Unit
) {
    var currentIndex by remember { mutableIntStateOf(0) }
    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    var score by remember { mutableIntStateOf(0) }
    var finished by remember { mutableStateOf(false) }

    if (finished) {
        QuizResultScreen(
            chapterTitle = quiz.chapterTitle,
            score = score,
            total = quiz.questions.size,
            onRetry = {
                currentIndex = 0
                selectedAnswer = null
                score = 0
                finished = false
            },
            onBackToChapters = onBackToChapters
        )
        return
    }

    val currentQuestion = quiz.questions[currentIndex]

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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header Section
            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "CHAPTER ${currentIndex + 1} QUIZ", // Example dynamic header
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = GoldAccent,
                        fontFamily = FontFamily.SansSerif,
                        letterSpacing = 2.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = quiz.chapterTitle,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Black,
                        color = CreamText,
                        fontFamily = FontFamily.Serif,
                        textAlign = TextAlign.Center,
                        lineHeight = 32.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Divider(
                        color = GoldAccent.copy(alpha = 0.3f),
                        thickness = 1.dp,
                        modifier = Modifier.fillMaxWidth(0.8f)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Question ${currentIndex + 1} of ${quiz.questions.size}",
                        fontSize = 14.sp,
                        color = CreamText.copy(alpha = 0.6f),
                        fontFamily = FontFamily.SansSerif,
                        letterSpacing = 1.sp
                    )
                }
            }

            // Question Card
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                    border = BorderStroke(1.dp, GoldAccent.copy(alpha = 0.3f))
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
                        Text(
                            text = currentQuestion.question,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = CreamText,
                            fontFamily = FontFamily.Serif,
                            lineHeight = 28.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            // Options
            items(currentQuestion.options.size) { optionIndex ->
                val option = currentQuestion.options[optionIndex]
                QuizOptionCard(
                    text = option,
                    selected = selectedAnswer == option,
                    onClick = { selectedAnswer = option }
                )
            }

            // Action Buttons
            item {
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (selectedAnswer == currentQuestion.correctAnswer) {
                            score++
                        }

                        if (currentIndex == quiz.questions.lastIndex) {
                            finished = true
                        } else {
                            currentIndex++
                            selectedAnswer = null
                        }
                    },
                    enabled = selectedAnswer != null,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GoldAccent,
                        contentColor = DarkBg,
                        disabledContainerColor = GoldAccent.copy(alpha = 0.3f),
                        disabledContentColor = DarkBg.copy(alpha = 0.5f)
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Text(
                        text = if (currentIndex == quiz.questions.lastIndex) "FINISH QUIZ" else "NEXT QUESTION",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        letterSpacing = 1.sp
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedButton(
                    onClick = onBackToChapters,
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = CreamText
                    ),
                    border = BorderStroke(1.dp, GoldAccent.copy(alpha = 0.5f)),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Text(
                        text = "BACK TO CHAPTERS",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        letterSpacing = 1.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun QuizOptionCard(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (selected) GoldAccent.copy(alpha = 0.15f) else Color.Transparent
        ),
        border = BorderStroke(
            width = if (selected) 1.5.dp else 1.dp,
            color = if (selected) GoldAccent else GoldAccent.copy(alpha = 0.3f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                fontSize = 16.sp,
                color = if (selected) GoldAccent else CreamText,
                fontFamily = FontFamily.Serif,
                lineHeight = 24.sp
            )
        }
    }
}

@Composable
private fun QuizResultScreen(
    chapterTitle: String,
    score: Int,
    total: Int,
    onRetry: () -> Unit,
    onBackToChapters: () -> Unit
) {
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, GoldAccent.copy(alpha = 0.5f), RoundedCornerShape(20.dp)),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color(0xFF1A1A1A).copy(alpha = 0.9f), DarkBg)
                            )
                        )
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "QUIZ COMPLETED",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = GoldAccent,
                        fontFamily = FontFamily.SansSerif,
                        letterSpacing = 2.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = chapterTitle,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = CreamText,
                        fontFamily = FontFamily.Serif,
                        textAlign = TextAlign.Center,
                        lineHeight = 30.sp
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    Divider(
                        color = GoldAccent.copy(alpha = 0.3f),
                        thickness = 1.dp,
                        modifier = Modifier.fillMaxWidth(0.5f)
                    )
                    Spacer(modifier = Modifier.height(32.dp))

                    Text(
                        text = "YOUR SCORE",
                        fontSize = 12.sp,
                        color = CreamText.copy(alpha = 0.6f),
                        fontFamily = FontFamily.SansSerif,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "$score / $total",
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Black,
                        color = GoldAccent,
                        fontFamily = FontFamily.Serif
                    )

                    Spacer(modifier = Modifier.height(48.dp))

                    Button(
                        onClick = onRetry,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = GoldAccent,
                            contentColor = DarkBg
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    ) {
                        Text(
                            text = "TRY AGAIN",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.SansSerif,
                            letterSpacing = 1.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedButton(
                        onClick = onBackToChapters,
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = CreamText
                        ),
                        border = BorderStroke(1.dp, GoldAccent.copy(alpha = 0.5f)),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    ) {
                        Text(
                            text = "BACK TO CHAPTERS",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.SansSerif,
                            letterSpacing = 1.sp
                        )
                    }
                }
            }
        }
    }
}