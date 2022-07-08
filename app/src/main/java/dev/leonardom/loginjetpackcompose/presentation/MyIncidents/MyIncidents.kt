package dev.leonardom.loginjetpackcompose.presentation.MyIncidents

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import dev.leonardom.loginjetpackcompose.RoomDatabase.Model.Notification
import dev.leonardom.loginjetpackcompose.viewModel.MainViewModel

@Composable
fun MyIncidents(
    navController: NavHostController,
    allNotifications: List<Notification>,
    viewModel: MainViewModel,
    searchResult: Notification
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)

    ){


        //TODO( CODIGO AQUI LISTADO DE TODOS LAS NOTIFICAIONES DE ROOM)
        LazyColumn(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ){
            item {
                Column(modifier = Modifier
                    .fillMaxSize()
                ){
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){
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
                            text = "Mis incidentes",
                            style = MaterialTheme.typography.h5.copy(
                                color = MaterialTheme.colors.primary
                            )
                        )
                    }
                }
            }
            val list = allNotifications
            items(list) {
                    notification ->
                Text(text = "${notification.id}")
                Text(text = "${notification.title}")
                Text(text = "${notification.idFirebase}")
                Text(text = "${notification.department}")
                Text(text = "${notification.date}")
                Text(text = "${notification.nameUser}")
            }
            /*
            item{
                //TODO( CODIGO PARA OBTENER UNA NOTIFICACION SEGUN ID)
                //los datos de la motificacion se guardan en la variable
                // searchResult
                Spacer(modifier = Modifier.height(20.dp))
                Column(){
                    viewModel.findNotificationById(1)
                    Text(text = "usuario con codigo 1")
                    Text(text = searchResult.title)
                    Text(text = searchResult.description)
                    Text(text = searchResult.idFirebase)
                    Text(text = searchResult.nameUser)
                }

            }
            */
        }
    }
}