package com.jp.thelifeandworksofrizal

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jp.thelifeandworksofrizal.ui.theme.*


// Reference Color Palette
private val DarkBg = Color(0xFF0A0C0A)
private val GoldAccent = Color(0xFFD4AF37)
private val CreamText = Color(0xFFFDFBF7)


@Composable
fun WrittenReportScreen(sections: List<ReportSection>) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBg)
    ) {
        // Warm Orange Radial Glow
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxSize()
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            DarkOrangeGlow.copy(alpha = 0.5f),
                            Color.Transparent
                        ),
                        radius = 1800f
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
            // Header
            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "WRITTEN REPORT",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Black,
                        color = CreamText,
                        fontFamily = FontFamily.Serif,
                        letterSpacing = 2.sp,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "This section summarizes Rizal's life, education, works, advocacy, and legacy.",
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
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            // Content Sections
            items(sections) { section ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, GoldAccent.copy(alpha = 0.3f), RoundedCornerShape(16.dp)),
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
                        Text(
                            text = section.title,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = GoldAccent,
                            fontFamily = FontFamily.Serif
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = section.content,
                            fontSize = 15.sp,
                            color = CreamText,
                            lineHeight = 24.sp,
                            fontFamily = FontFamily.Serif,
                            textAlign = TextAlign.Justify
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MembersScreen(members: List<MemberInfo>) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBg)
    ) {
        // Warm Orange Radial Glow
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxSize()
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            DarkOrangeGlow.copy(alpha = 0.4f), // Softer warm orange glow in the center
                            DarkBg.copy(alpha = 0.85f),        // Smooth transition to dark
                            DarkBg                             // Fully solid dark border at the extremes
                        ),
                        radius = 1800f
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
            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "PROJECT MEMBERS",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Black,
                        color = CreamText,
                        fontFamily = FontFamily.Serif,
                        letterSpacing = 2.sp,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "The team behind this interactive exhibit.",
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
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            items(members) { member ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, GoldAccent.copy(alpha = 0.2f), RoundedCornerShape(12.dp)),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(Color(0xFF1A1A1A).copy(alpha = 0.6f), DarkBg)
                                )
                            )
                            .padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .border(1.dp, GoldAccent.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
                                .background(DarkOrangeGlow, RoundedCornerShape(12.dp)), // Updated to orange
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = member.name.take(1).uppercase(),
                                color = GoldAccent,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Serif
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(
                                text = member.name,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = CreamText,
                                fontFamily = FontFamily.Serif
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = member.role.uppercase(),
                                fontSize = 12.sp,
                                color = GoldAccent.copy(alpha = 0.8f),
                                fontFamily = FontFamily.SansSerif,
                                letterSpacing = 1.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AiAssistantScreen(viewModel: ChatViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    var question by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBg)
    ) {
        // Subtle Warm Orange Radial Glow
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxSize()
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            DarkOrangeGlow.copy(alpha = 0.4f),
                            Color.Transparent
                        ),
                        radius = 1200f
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Header is now inside the scrollable LazyColumn
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "AI ASSISTANT",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Black,
                            color = CreamText,
                            fontFamily = FontFamily.Serif,
                            letterSpacing = 2.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Ask questions about Rizal's life, trial, and legacy.",
                            fontSize = 14.sp,
                            color = CreamText.copy(alpha = 0.8f),
                            fontFamily = FontFamily.Serif,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Divider(
                            color = GoldAccent.copy(alpha = 0.3f),
                            thickness = 1.dp,
                            modifier = Modifier.fillMaxWidth(0.8f)
                        )
                    }
                }

                items(uiState.messages) { message ->
                    ChatBubble(message = message)
                }

                if (uiState.isLoading) {
                    item {
                        Surface(
                            shape = RoundedCornerShape(16.dp),
                            color = Color(0xFF1A1A1A),
                            border = BorderStroke(1.dp, GoldAccent.copy(alpha = 0.2f))
                        ) {
                            Text(
                                text = "Consulting archives...",
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                                fontSize = 14.sp,
                                color = GoldAccent,
                                fontFamily = FontFamily.Serif,
                                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                            )
                        }
                    }
                }
            }

            // Input Area: TextField and Button side-by-side
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val isInputValid = question.isNotBlank() && !uiState.isLoading

                // Input Area
                OutlinedTextField(
                    value = question,
                    onValueChange = { question = it },
                    modifier = Modifier.weight(1f),
                    label = { Text("Ask about Rizal", fontFamily = FontFamily.Serif) },
                    placeholder = { Text("e.g. Why was he exiled?", fontFamily = FontFamily.Serif) },
                    enabled = !uiState.isLoading,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = GoldAccent,
                        unfocusedBorderColor = GoldAccent.copy(alpha = 0.3f),
                        focusedTextColor = CreamText,
                        unfocusedTextColor = CreamText,
                        cursorColor = GoldAccent,
                        focusedLabelColor = GoldAccent,
                        unfocusedLabelColor = CreamText.copy(alpha = 0.6f),
                        focusedPlaceholderColor = CreamText.copy(alpha = 0.4f),
                        unfocusedPlaceholderColor = CreamText.copy(alpha = 0.4f),
                        focusedContainerColor = DarkBg.copy(alpha = 0.5f),
                        unfocusedContainerColor = DarkBg.copy(alpha = 0.5f)
                    ),
                    shape = RoundedCornerShape(12.dp)
                )

                // Metallic Send Button
                Button(
                    onClick = {
                        viewModel.sendMessage(question)
                        question = ""
                    },
                    modifier = Modifier
                        .size(56.dp), // Square button to sit beside TextField
                    enabled = isInputValid,
                    contentPadding = PaddingValues(0.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent
                    )
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = if (isInputValid) {
                                    Brush.horizontalGradient(
                                        colors = listOf(Color(0xFFB8860B), GoldAccent, Color(0xFFB8860B))
                                    )
                                } else {
                                    androidx.compose.ui.graphics.SolidColor(GoldAccent.copy(alpha = 0.3f))
                                }
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        if (uiState.isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp),
                                strokeWidth = 2.dp,
                                color = DarkBg.copy(alpha = 0.5f)
                            )
                        } else {
                            Icon(
                                imageVector = Icons.AutoMirrored.Rounded.Send,
                                contentDescription = "Send",
                                tint = if (isInputValid) DarkBg else DarkBg.copy(alpha = 0.5f),
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ChatBubble(message: ChatMessage) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (message.isUser) Arrangement.End else Arrangement.Start
    ) {
        Box(
            modifier = Modifier.widthIn(max = 300.dp)
        ) {
            Surface(
                shape = RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp,
                    bottomStart = if (message.isUser) 16.dp else 0.dp,
                    bottomEnd = if (message.isUser) 0.dp else 16.dp
                ),
                color = if (message.isUser) DarkBg else Color(0xFF1A1A1A),
                border = if (message.isUser) BorderStroke(1.dp, GoldAccent.copy(alpha = 0.5f))
                else BorderStroke(1.dp, GoldAccent.copy(alpha = 0.1f))
            ) {
                Text(
                    text = message.text,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                    fontSize = 15.sp,
                    color = CreamText,
                    lineHeight = 22.sp,
                    fontFamily = FontFamily.Serif
                )
            }
        }
    }
}
