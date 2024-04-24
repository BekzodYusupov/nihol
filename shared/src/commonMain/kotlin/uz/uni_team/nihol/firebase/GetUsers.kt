package uz.uni_team.nihol.firebase

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore

suspend fun getUsers() {
    val firebaseFirestore = Firebase.firestore
    try {
        val userResponse =
            firebaseFirestore.collection("order").get()
        userResponse.documents.map {
            val data = it.get<String>("count")
            println("Firebase response test : ${data}")
        }
    } catch (e: Exception) {
        println("firebase response: Exception")
        throw e
    }
}