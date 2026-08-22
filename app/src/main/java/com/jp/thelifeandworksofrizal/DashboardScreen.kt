package com.jp.thelifeandworksofrizal

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jp.thelifeandworksofrizal.ui.theme.*

@Composable
fun DashboardScreen(
    onStoryClick: () -> Unit,
    onQuizClick: () -> Unit,
    onReportClick: () -> Unit,
    onMembersClick: () -> Unit,
    onAiAssistantClick: () -> Unit,
    onContinueChapterClick: (Int) -> Unit,
    userViewModel: UserViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val profile = userViewModel.profile.value

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBg)
    ) {
        // 1. Faded Background Image
        Image(
            painter = painterResource(id = R.drawable.splash_background),
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            alpha = 0.1f,
            modifier = Modifier.fillMaxSize()
        )

        // 2. Vignette & Glow Overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            DarkOrangeGlow.copy(alpha = 0.4f),
                            DarkBg.copy(alpha = 0.85f),
                            DarkBg
                        ),
                        radius = 1800f
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = if (profile.fullName.isNotBlank())
                            "Hi, ${profile.fullName.substringBefore(" ")}"
                        else
                            "Rizal Learning",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Black,
                        color = CreamText,
                        fontFamily = FontFamily.Serif,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Explore stories, quizzes, and reports",
                        fontSize = 14.sp,
                        color = GoldAccent.copy(alpha = 0.8f),
                        fontFamily = FontFamily.Serif
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.profilepic),
                    contentDescription = "User Profile",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(48.dp)
                        .border(1.dp, GoldAccent, CircleShape)
                        .padding(4.dp)
                        .clip(CircleShape)
                        .background(GoldAccent.copy(alpha = 0.2f))
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Feature grid
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                DashboardFeatureCard(
                    title = "Story",
                    subtitle = "Read life stories and works of Rizal",
                    icon = Icons.Rounded.MenuBook,
                    onClick = onStoryClick,
                    modifier = Modifier.weight(1f)
                )
                DashboardFeatureCard(
                    title = "Quiz",
                    subtitle = "Test your knowledge with fun quizzes",
                    icon = Icons.Rounded.Create,
                    onClick = onQuizClick,
                    modifier = Modifier.weight(1f)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                DashboardFeatureCard(
                    title = "Reports",
                    subtitle = "View your activity and learning reports",
                    icon = Icons.Rounded.Article,
                    onClick = onReportClick,
                    modifier = Modifier.weight(1f)
                )
                DashboardFeatureCard(
                    title = "Members",
                    subtitle = "See classmates and class activities",
                    icon = Icons.Rounded.Groups,
                    onClick = onMembersClick,
                    modifier = Modifier.weight(1f)
                )
            }

            DashboardFeatureCard(
                title = "AI Assistant",
                subtitle = "Ask questions and get help about Rizal anytime",
                icon = Icons.Rounded.AutoAwesome,
                onClick = onAiAssistantClick,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Continue Learning card — only shows once the user has read something
            if (profile.lastChapterIndex != null) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, GoldAccent.copy(alpha = 0.3f), RoundedCornerShape(16.dp)),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent)
                ) {
                    Column(
                        modifier = Modifier
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(Color(0xFF1A1A1A), Color(0xFF0F0F0F))
                                )
                            )
                            .padding(20.dp)
                    ) {
                        Text(
                            text = "Continue Learning",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = GoldAccent,
                            fontFamily = FontFamily.Serif
                        )
                        Text(
                            text = "Pick up where you left off",
                            fontSize = 12.sp,
                            color = CreamText.copy(alpha = 0.7f)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .border(1.dp, GoldAccent.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
                                    .background(DarkBg, RoundedCornerShape(12.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.MenuBook,
                                    contentDescription = "Book",
                                    tint = GoldAccent
                                )
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = profile.lastChapterTitle ?: "",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = CreamText,
                                    fontFamily = FontFamily.Serif,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = "Recently read",
                                    fontSize = 12.sp,
                                    color = CreamText.copy(alpha = 0.7f)
                                )
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            IconButton(
                                onClick = { onContinueChapterClick(profile.lastChapterIndex!!) },
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(GoldAccent.copy(alpha = 0.1f), CircleShape)
                            ) {
                                Icon(
                                    Icons.Rounded.ArrowForward,
                                    contentDescription = "Continue",
                                    tint = GoldAccent
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun DashboardFeatureCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .height(160.dp)
            .border(1.dp, GoldAccent.copy(alpha = 0.3f), RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF1A1A1A).copy(alpha = 0.8f),
                            Color(0xFF0A0C0A).copy(alpha = 0.9f)
                        )
                    )
                )
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .border(1.dp, GoldAccent.copy(alpha = 0.4f), RoundedCornerShape(12.dp))
                    .background(DarkBg, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = GoldAccent,
                    modifier = Modifier.size(24.dp)
                )
            }
            Column {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    color = CreamText,
                    fontSize = 18.sp,
                    fontFamily = FontFamily.Serif
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = subtitle,
                    fontSize = 12.sp,
                    color = CreamText.copy(alpha = 0.6f),
                    maxLines = 2,
                    lineHeight = 16.sp,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}