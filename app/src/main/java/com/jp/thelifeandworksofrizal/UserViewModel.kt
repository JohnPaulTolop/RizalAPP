package com.jp.thelifeandworksofrizal

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration

data class UserProfile(
    val fullName: String = "",
    val photoUrl: String = "",
    val isDarkMode: Boolean = true,
    val lastChapterIndex: Int? = null,
    val lastChapterTitle: String? = null
)

class UserViewModel : ViewModel() {

    // Firebase Authentication
    private val auth = FirebaseAuth.getInstance()

    // Firestore Database
    private val firestore = FirebaseFirestore.getInstance()

    // Firestore realtime listener
    private var listener: ListenerRegistration? = null

    // Current user's profile
    var profile = mutableStateOf(UserProfile())
        private set

    init {
        listenToProfile()
    }


    private fun listenToProfile() {

        val uid = auth.currentUser?.uid ?: return

        listener = firestore
            .collection("User")
            .document(uid)
            .addSnapshotListener { snapshot, error ->

                // Stop if Firestore returns an error
                if (error != null) {
                    return@addSnapshotListener
                }

                // Stop if the document does not exist
                if (snapshot == null || !snapshot.exists()) {
                    return@addSnapshotListener
                }

                profile.value = UserProfile(

                    fullName = snapshot.getString("fullName") ?: "",

                    photoUrl = snapshot.getString("photoUrl") ?: "",

                    isDarkMode = snapshot.getBoolean("isDarkMode") ?: true,

                    lastChapterIndex =
                        snapshot.getLong("lastChapterIndex")?.toInt(),

                    lastChapterTitle =
                        snapshot.getString("lastChapterTitle")
                )
            }
    }


    fun updateLastReadChapter(
        chapterIndex: Int,
        chapterTitle: String
    ) {

        val uid = auth.currentUser?.uid ?: return

        firestore
            .collection("User")
            .document(uid)
            .update(
                mapOf(
                    "lastChapterIndex" to chapterIndex,
                    "lastChapterTitle" to chapterTitle,
                    "lastReadAt" to com.google.firebase.Timestamp.now()
                )
            )
    }


    fun uploadProfilePicture(imageBytes: ByteArray) {


        val uid = auth.currentUser?.uid ?: return


        val path = "$uid/profile.jpg"


    }


    fun updateFullName(
        newName: String,
        onSuccess: () -> Unit = {},
        onError: (String) -> Unit = {}
    ) {

        val uid = auth.currentUser?.uid ?: return

        val cleanedName = newName.trim()

        if (cleanedName.isBlank()) {
            onError("Name cannot be empty.")
            return
        }

        firestore
            .collection("User")
            .document(uid)
            .update("fullName", cleanedName)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { exception ->
                onError(
                    exception.localizedMessage
                        ?: "Failed to update name."
                )
            }
    }


    fun updateDarkMode(
        isDarkMode: Boolean
    ) {

        val uid = auth.currentUser?.uid ?: return

        firestore
            .collection("User")
            .document(uid)
            .update("isDarkMode", isDarkMode)
    }


    fun updateProfilePictureUrl(
        photoUrl: String
    ) {

        val uid = auth.currentUser?.uid ?: return

        firestore
            .collection("User")
            .document(uid)
            .update("photoUrl", photoUrl)
    }


    override fun onCleared() {
        super.onCleared()
        listener?.remove()
    }
}