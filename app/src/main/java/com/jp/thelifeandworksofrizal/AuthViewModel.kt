package com.jp.thelifeandworksofrizal

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class AuthViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    var isLoading = mutableStateOf(false)
        private set
    var errorMessage = mutableStateOf<String?>(null)
        private set

    fun signUp(
        email: String,
        password: String,
        fullName: String,
        onSuccess: () -> Unit
    ) {
        if (email.isBlank() || password.isBlank() || fullName.isBlank()) {
            errorMessage.value = "Please fill in all fields."
            return
        }
        isLoading.value = true
        errorMessage.value = null

        viewModelScope.launch {
            try {
                val result = auth.createUserWithEmailAndPassword(email, password).await()
                val uid = result.user?.uid ?: throw Exception("No UID returned")

                // Create the user's profile document in Firestore, keyed by their Auth UID
                val userProfile = hashMapOf(
                    "fullName" to fullName,
                    "email" to email,
                    "createdAt" to com.google.firebase.Timestamp.now()
                )
                firestore.collection("User").document(uid).set(userProfile).await()

                isLoading.value = false
                onSuccess()
            } catch (e: Exception) {
                isLoading.value = false
                errorMessage.value = e.localizedMessage ?: "Sign up failed."
            }
        }
    }

    fun login(
        email: String,
        password: String,
        onSuccess: () -> Unit
    ) {
        if (email.isBlank() || password.isBlank()) {
            errorMessage.value = "Please enter your email and password."
            return
        }
        isLoading.value = true
        errorMessage.value = null

        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password).await()
                isLoading.value = false
                onSuccess()
            } catch (e: Exception) {
                isLoading.value = false
                errorMessage.value = e.localizedMessage ?: "Login failed."
            }
        }
    }

    fun currentUser() = auth.currentUser
}