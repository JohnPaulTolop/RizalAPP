package com.jp.thelifeandworksofrizal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.GenerativeBackend
import com.google.firebase.ai.type.content
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {

    private
    val model by lazy {
        Firebase.ai(backend = GenerativeBackend.googleAI()).generativeModel(
            modelName = "gemini-3.7-flash",
            systemInstruction = content {
                text(
                    """
                    You are the AI assistant inside an Android learning app about Jose Rizal.
                    Answer only questions related to Jose Rizal, his life, education, writings,
                    novels, exile, trial, execution, historical context, and legacy.
                    Use clear student-friendly explanations. Keep answers concise unless the user
                    asks for more detail. If the question is unrelated, politely redirect the user
                    back to Jose Rizal or Philippine history.
                    """.trimIndent()
                )
            }
        )
    }

    private val chat by lazy { model.startChat() }

    private val _uiState = MutableStateFlow(
        ChatUiState(
            messages = listOf(
                ChatMessage(
                    text = "Ask something about Rizal's life, education, novels, exile, or legacy.",
                    isUser = false
                )
            )
        )
    )
    val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()

    fun sendMessage(text: String) {
        val prompt = text.trim()
        if (prompt.isBlank() || _uiState.value.isLoading) return

        appendMessage(ChatMessage(text = prompt, isUser = true))
        _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

        viewModelScope.launch {
            try {
                val response = chat.sendMessage(prompt)
                val answer = response.text?.trim().takeUnless { it.isNullOrBlank() }
                    ?: "I couldn't generate a response. Please try again."

                appendMessage(ChatMessage(text = answer, isUser = false))
            } catch (e: Exception) {
                val errorText = e.localizedMessage ?: "Unknown Firebase AI Logic error."
                _uiState.value = _uiState.value.copy(errorMessage = errorText)
                appendMessage(
                    ChatMessage(
                        text = "Sorry, I couldn't connect to the AI service. $errorText",
                        isUser = false
                    )
                )
            } finally {
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }

    private fun appendMessage(message: ChatMessage) {
        _uiState.value = _uiState.value.copy(messages = _uiState.value.messages + message)
    }
}

data class ChatUiState(
    val messages: List<ChatMessage> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

data class ChatMessage(
    val text: String,
    val isUser: Boolean = true
)
