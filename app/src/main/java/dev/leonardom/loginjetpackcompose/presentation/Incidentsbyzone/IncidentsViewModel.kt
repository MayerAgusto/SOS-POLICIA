package dev.leonardom.loginjetpackcompose.presentation.Incidentsbyzone

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dev.leonardom.loginjetpackcompose.RoomDatabase.Model.Notification
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow




class IncidentsViewModel (departamento : String?) : ViewModel(){
    private val db = Firebase.firestore
    private val depa = departamento

    fun fetchPosts() = callbackFlow {
        val collection = db.collection("notifications")

        val snapshotListener = collection.whereEqualTo("department", depa).addSnapshotListener{ value, error ->

            val response = if (error == null && value != null) {
                val data = value.documents.map { doc ->
                    Notification.toObject(doc)
                }
                Resource.success(data)
            } else {
                Resource.error(error.toString(), null)
            }

            this.trySend(response).isSuccess
        }

        awaitClose() {
            snapshotListener.remove()
        }
    }
}