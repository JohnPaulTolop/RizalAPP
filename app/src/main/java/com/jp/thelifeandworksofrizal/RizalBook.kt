package com.jp.thelifeandworksofrizal

// Base Chapter class
open class Chapter(
    open val title: String,
    open val subtitle: String,
    open val content: String
)

// RizalChapter inherits from Chapter
data class RizalChapter(
    val id: Int,
    override val title: String,
    override val subtitle: String,
    override val content: String
) : Chapter(title, subtitle, content)

// RizalBook remains the same
data class RizalBook(
    val title: String,
    val chapters: List<RizalChapter>
)

data class QuizQuestion(
    val question: String,
    val options: List<String>,
    val correctAnswer: String
)

data class ChapterQuiz(
    val chapterId: Int,
    val chapterTitle: String,
    val questions: List<QuizQuestion>
)

data class MemberInfo(
    val name: String,
    val role: String
)

data class ReportSection(
    val title: String,
    val content: String
)