package dev.leonardom.loginjetpackcompose.presentation.Incidentsbyzone




import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import com.google.firebase.firestore.FirebaseFirestore
import dev.leonardom.loginjetpackcompose.RoomDatabase.Model.Notification
import dev.leonardom.loginjetpackcompose.presentation.components.CardNotificacion
import dev.leonardom.loginjetpackcompose.presentation.components.IncidentsZone
import dev.leonardom.loginjetpackcompose.viewModel.MainViewModel
import kotlinx.coroutines.channels.awaitClose
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.foundation.lazy.items



@Composable
fun IncidentsByZone(viewModel: IncidentsViewModel,
                    navController: NavHostController,) {
    val centroResource by viewModel.fetchPosts().collectAsState(initial = Resource.loading(null))
    val centrodata = centroResource.data ?: emptyList()
    if (centroResource.status == Status.ERROR) {
        Text("Error: ${centroResource.message}")
    } else if (centroResource.status == Status.LOADING) {
        Text("Loading ....")
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)

        ) {


            //TODO( CODIGO AQUI LISTADO DE TODOS LAS NOTIFICAIONES DE ROOM)
            LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(
                                onClick = {
                                    navController.navigate("main")
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "Back Icon",
                                    tint = MaterialTheme.colors.primary
                                )
                            }

                            Text(
                                text = "Mis incidentes de mi zona",
                                style = MaterialTheme.typography.h5.copy(
                                    color = MaterialTheme.colors.primary
                                )
                            )
                        }
                    }
                }
                items(centrodata) { data ->
                    if (data != null) {
                        IncidentsZone(data)
                    }
                }
            }

        }
    }
}