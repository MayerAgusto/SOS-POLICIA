package dev.leonardom.loginjetpackcompose.presentation.components


import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Public
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import com.google.firebase.firestore.FirebaseFirestore
import dev.leonardom.loginjetpackcompose.DataStore.DataStore
import dev.leonardom.loginjetpackcompose.RoomDatabase.Model.Notification
import dev.leonardom.loginjetpackcompose.viewModel.MainViewModel
import org.intellij.lang.annotations.JdkConstants

@Composable
fun CardNotificacion(
    Notificacion  : Notification,
    viewModel: MainViewModel,
    db: FirebaseFirestore,

){
    Surface(shape = RoundedCornerShape(8.dp),
        elevation = 8.dp,
        modifier = Modifier.padding(8.dp)) {
        Column(modifier = Modifier.padding(8.dp),
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,

                ) {
                Column() {
                    Text(buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        ) {
                            append("Titulo : ")
                        }
                        append(Notificacion.title)
                    }
                    )
                    Text(buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        ) {
                            append("Descripcion : ")
                        }
                        append(Notificacion.description)
                    }
                    )
                    Text(buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        ) {
                            append("Depertamento : ")
                        }
                        append(Notificacion.department)
                    }
                    )
                    Text(buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        ) {
                            append("Delito : ")
                        }
                        append(Notificacion.kindCrime)
                    }
                    )
                    Text(buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        ) {
                            append("Ciudad : ")
                        }
                        append(Notificacion.city)
                    }
                    )
                    Text(buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        ) {
                            append("Estado : ")
                        }
                        append(Notificacion.status)
                    }
                    )
                    Text(buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        ) {
                            append("Fecha : ")
                        }
                        append(Notificacion.date)
                    }
                    )


                }

            }
            Row(verticalAlignment = Alignment.CenterVertically,) {
                IconButton(
                    onClick = { viewModel.deleteNotification(Notificacion) },
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = null,
                        tint = Color.Red,
                    )
                }
                IconButton(onClick = {
                    db.collection("notifications")
                        .add(Notificacion)
                        .addOnSuccessListener { documentReference ->
                            Log.d(
                                "FIREBASE",
                                "DocumentSnapshot added with ID: ${documentReference.id}"
                            )
                            Notificacion.status = "PUBLICADO"
                            Notificacion.savefirestoreID(documentReference.id)
                            viewModel.insertNotification(Notificacion)
                        }
                }) {
                    Icon(
                        imageVector = Icons.Filled.Public,
                        contentDescription = null,
                        tint = Color.Green
                    )
                }

            }
        }

    }
}






