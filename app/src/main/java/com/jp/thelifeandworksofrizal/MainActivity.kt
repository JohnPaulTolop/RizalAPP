package com.jp.thelifeandworksofrizal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.google.firebase.FirebaseApp
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.automirrored.filled.ShowChart
import com.google.firebase.Firebase
import com.google.firebase.appcheck.appCheck
import com.google.firebase.appcheck.debug.DebugAppCheckProviderFactory

// Reference Color Palette from Vermeer Design
private val DarkBg = Color(0xFF0A0C0A)
private val GoldAccent = Color(0xFFD4AF37)
private val CreamText = Color(0xFFFDFBF7)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(
                android.graphics.Color.TRANSPARENT
            )
        )
        FirebaseApp.initializeApp(this)
        Firebase.appCheck.installAppCheckProviderFactory(
            DebugAppCheckProviderFactory.getInstance()
        )
        setContent {
            @OptIn(ExperimentalMaterial3Api::class)

            RizalApp()
        }

    }
}

// Your existing Screen sealed class
sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Signup : Screen("signup")
    object Dashboard : Screen("dashboard")
    object Story : Screen("story")
    object ChapterDetail : Screen("chapterDetail/{chapterIndex}") {
        const val ARG = "chapterIndex"
        fun createRoute(chapterIndex: Int) = "chapterDetail/$chapterIndex"
    }
    object QuizChapters : Screen("quiz_chapters")
    object Quiz : Screen("quiz/{chapterIndex}") {
        const val ARG = "chapterIndex"
        fun createRoute(chapterIndex: Int) = "quiz/$chapterIndex"
    }
    object WrittenReport : Screen("written_report")
    object Members : Screen("members")
    object AiAssistant : Screen("ai_assistant")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RizalBottomNavigationBar(selectedTab: Int, onTabSelected: (Int) -> Unit) {
    NavigationBar(
        containerColor = DarkBg,
        contentColor = CreamText,
        tonalElevation = 8.dp
    ) {
        val navColors = NavigationBarItemDefaults.colors(
            selectedIconColor = DarkBg,
            selectedTextColor = GoldAccent,
            indicatorColor = GoldAccent,
            unselectedIconColor = CreamText.copy(alpha = 0.5f),
            unselectedTextColor = CreamText.copy(alpha = 0.5f)
        )

        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("Home", fontFamily = FontFamily.Serif) },
            selected = selectedTab == 0,
            onClick = { onTabSelected(0) },
            colors = navColors
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Book, contentDescription = "Chapters") },
            label = { Text("Chapters", fontFamily = FontFamily.Serif) },
            selected = selectedTab == 1,
            onClick = { onTabSelected(1) },
            colors = navColors
        )
        NavigationBarItem(
            icon = { Icon(Icons.AutoMirrored.Filled.ShowChart, contentDescription = "Progress") },
            label = { Text("Progress", fontFamily = FontFamily.Serif) },
            selected = selectedTab == 2,
            onClick = { onTabSelected(2) },
            colors = navColors
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Person, contentDescription = "Profile") },
            label = { Text("Profile", fontFamily = FontFamily.Serif) },
            selected = selectedTab == 3,
            onClick = { onTabSelected(3) },
            colors = navColors
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RizalApp() {
    val navController = rememberNavController()
    var selectedTab by remember { mutableIntStateOf(0) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        containerColor = DarkBg, // Applies the dark theme globally
        topBar = {
            // Hide default top bar on Dashboard, Get Started, Login, and Signup
            if (currentRoute != Screen.Dashboard.route &&
                currentRoute != "get_started" &&
                currentRoute != Screen.Login.route &&
                currentRoute != Screen.Signup.route
            ) {

                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = getScreenTitle(currentRoute),
                            color = CreamText,
                            fontFamily = FontFamily.Serif
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = GoldAccent
                            )
                        }
                    },
                    actions = {
                        // Add the bookmark icon ONLY when we are on the ChapterDetail screen
                        if (currentRoute?.startsWith("chapterDetail") == true) {
                            IconButton(onClick = { /* TODO: Handle Bookmark */ }) {
                                Icon(
                                    Icons.Outlined.BookmarkBorder,
                                    contentDescription = "Bookmark",
                                    tint = GoldAccent
                                )
                            }
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.Transparent, // Let screen backgrounds show through
                        scrolledContainerColor = DarkBg
                    )
                )
            }
        },
        bottomBar = {
            if (currentRoute != "get_started" &&
                currentRoute != Screen.Login.route &&
                currentRoute != Screen.Signup.route
            ) {
                RizalBottomNavigationBar(selectedTab = selectedTab, onTabSelected = { tab: Int ->
                    selectedTab = tab
                    when (tab) {
                        0 -> navController.navigate(Screen.Dashboard.route)
                        1 -> navController.navigate(Screen.Story.route)
                        2 -> navController.navigate(Screen.QuizChapters.route)
                        3 -> navController.navigate(Screen.Members.route)
                    }
                })
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "get_started",
            modifier = Modifier.padding(innerPadding)
        ) {
            // Get Started Screen first
            composable("get_started") { GetStartedScreen(navController) }

            // Login
            composable(Screen.Login.route) { LoginScreen(navController) }

            // Signup
            composable(Screen.Signup.route) { SignupScreen(navController) }

            // Dashboard
            composable(Screen.Dashboard.route) {
                DashboardScreen(
                    onStoryClick = { navController.navigate(Screen.Story.route) },
                    onQuizClick = { navController.navigate(Screen.QuizChapters.route) },
                    onReportClick = { navController.navigate(Screen.WrittenReport.route) },
                    onMembersClick = { navController.navigate(Screen.Members.route) },
                    onAiAssistantClick = { navController.navigate(Screen.AiAssistant.route) },
                    onContinueChapterClick = { index ->
                        navController.navigate(Screen.ChapterDetail.createRoute(index))
                    }
                )
            }

            // Story
            composable(Screen.Story.route) {
                StoryScreen(book = RizalData.book) { chapterIndex ->
                    navController.navigate(Screen.ChapterDetail.createRoute(chapterIndex))
                }
            }

            // Chapter Detail
            composable(
                route = Screen.ChapterDetail.route,
                arguments = listOf(navArgument(Screen.ChapterDetail.ARG) { type = NavType.IntType })
            ) { backStackEntry ->
                val chapterIndex = backStackEntry.arguments?.getInt(Screen.ChapterDetail.ARG) ?: 0
                val chapter = RizalData.book.chapters[chapterIndex]
                val userViewModel: UserViewModel = viewModel()

                // Mark this chapter as "last read" the moment it's opened
                LaunchedEffect(chapterIndex) {
                    userViewModel.updateLastReadChapter(chapterIndex, chapter.title)
                }

                ChapterDetailScreen(chapter = chapter)
            }

            // Quiz Chapters
            composable(Screen.QuizChapters.route) {
                QuizChapterSelectionScreen(
                    chapterQuizzes = RizalData.chapterQuizzes,
                    onChapterSelected = { index -> navController.navigate(Screen.Quiz.createRoute(index)) }
                )
            }

            // Quiz
            composable(
                route = Screen.Quiz.route,
                arguments = listOf(navArgument(Screen.Quiz.ARG) { type = NavType.IntType })
            ) { backStackEntry ->
                val chapterIndex = backStackEntry.arguments?.getInt(Screen.Quiz.ARG) ?: 0
                val selectedQuiz = RizalData.chapterQuizzes.getOrElse(chapterIndex) { RizalData.chapterQuizzes.first() }
                QuizScreen(quiz = selectedQuiz, onBackToChapters = { navController.popBackStack() })
            }

            // Written Report
            composable(Screen.WrittenReport.route) {
                WrittenReportScreen(sections = RizalData.reportSections)
            }

            // Members
            composable(Screen.Members.route) {
                MembersScreen(members = RizalData.members)
            }

            // AI Assistant
            composable(Screen.AiAssistant.route) {
                AiAssistantScreen()
            }
        }
    }
}

private fun getScreenTitle(route: String?): String {
    return when {
        route == Screen.Story.route -> "Story of Rizal"
        route?.startsWith("chapterDetail") == true -> "Chapter Detail"
        route == Screen.QuizChapters.route -> "Quiz Chapters"
        route?.startsWith("quiz/") == true -> "Quiz"
        route == Screen.WrittenReport.route -> "Written Report"
        route == Screen.Members.route -> "Members"
        route == Screen.AiAssistant.route -> "AI Assistant"
        else -> "The Life and Works of Rizal"
    }
}