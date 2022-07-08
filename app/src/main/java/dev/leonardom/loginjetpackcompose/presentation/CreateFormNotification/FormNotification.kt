package dev.leonardom.loginjetpackcompose.presentation.CreateFormNotification

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.okhttp.Dispatcher
import dev.leonardom.loginjetpackcompose.DataStore.DataStore
import dev.leonardom.loginjetpackcompose.RoomDatabase.Model.Notification
import dev.leonardom.loginjetpackcompose.presentation.components.AutoComplete
import dev.leonardom.loginjetpackcompose.presentation.components.RoundedButton
import dev.leonardom.loginjetpackcompose.presentation.components.TransparentTextField
import dev.leonardom.loginjetpackcompose.viewModel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.*

@Composable
fun FormNotification(
    navController: NavHostController,
    dataStore: DataStore,
    viewModel: MainViewModel,
    db: FirebaseFirestore
) {
    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()

    val userDepartment = dataStore.getDepartment.collectAsState(initial = "")
    val userProvince = dataStore.getProvince.collectAsState(initial = "")
    val userCity  =dataStore.getCity.collectAsState(initial = "")
    val userName = dataStore.getName.collectAsState(initial = "")
    val userEmail = dataStore.getEmail.collectAsState(initial = "")


    val titleValue = remember { mutableStateOf("") }
    val descriptionValue = remember { mutableStateOf("") }
    val ilicitValue = remember { mutableStateOf("") }

    val categoryList = "Denuncia, Aviso comunitario"
    val categoryTitle = "Ingrese la categoria"
    var categoryItem = remember { mutableStateOf("") }

    val addressValue = remember { mutableStateOf("") }

    val departamentList = "Amazonas, Ancash, Apurimac, Arequipa, Ayacucho, Cajamarca, Callao, Cusco, Huancavelica, Huanuco, Ica, Junín, La Libertad, Lambayeque, Lima, Loreto, Madre de Dios, Moquegua, Pasco, Piura, Puno, San Martín, Tacna, Tumbes, Ucayali"
    var departamentTitle  ="Departamento"
    var departamentItem = remember { mutableStateOf("") }
    departamentItem.value  =userDepartment.value.toString()

    val provinceValue = remember { mutableStateOf("") }
    provinceValue.value = userProvince.value.toString()
    val cityValue = remember { mutableStateOf("") }
    cityValue.value = userCity.value.toString()

    val levelIncidentList = "Leve, Moderado, Grave"
    val levelIncidentTitle = "Levedad del incidente"
    var levelIncidentItem = remember { mutableStateOf("") }

    val stateList = "No Publicado, Publicado"
    val stateTitle = "Estado del aviso"
    var stateItem = remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
    ){
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
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
                    text = "Nuevo Incidente",
                    style = MaterialTheme.typography.h5.copy(
                        color = MaterialTheme.colors.primary
                    )
                )
            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){
                TransparentTextField(
                    textFieldValue = titleValue,
                    textLabel = "Titulo",
                    keyboardType = KeyboardType.Text,
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    imeAction = ImeAction.Next
                )
                TransparentTextField(
                    textFieldValue = descriptionValue,
                    textLabel = "Descripcion",
                    keyboardType = KeyboardType.Text,
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    imeAction = ImeAction.Next
                )
                TransparentTextField(
                    textFieldValue = ilicitValue,
                        textLabel = "Tipo de delito",
                    keyboardType = KeyboardType.Text,
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    imeAction = ImeAction.Next
                )
                AutoComplete(selectedItem = categoryItem, text = categoryTitle, data = categoryList)
                TransparentTextField(
                    textFieldValue = addressValue,
                    textLabel = "Direccion",
                    keyboardType = KeyboardType.Text,
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    imeAction = ImeAction.Next
                )
                AutoComplete(selectedItem = departamentItem, text = departamentTitle, data = departamentList)
                TransparentTextField(
                    textFieldValue = provinceValue,
                    textLabel = "Provincia",
                    keyboardType = KeyboardType.Text,
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    imeAction = ImeAction.Next
                )
                TransparentTextField(
                    textFieldValue = cityValue,
                    textLabel = "Distrito",
                    keyboardType = KeyboardType.Text,
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    imeAction = ImeAction.Next
                )
                AutoComplete(selectedItem = levelIncidentItem, text = levelIncidentTitle, data = levelIncidentList)
                AutoComplete(selectedItem = stateItem, text = stateTitle, data = stateList)
                Spacer(modifier = Modifier.height(16.dp))

                Row() {
                    RoundedButton(
                        text = "Guardar",
                        displayProgressBar = false,
                        onClick = {
                            val c = Calendar.getInstance()
                            val year = c.get(Calendar.YEAR)
                            val month = c.get(Calendar.MONTH)
                            val day = c.get(Calendar.DAY_OF_MONTH)
                            val hour = c.get(Calendar.HOUR_OF_DAY)
                            val minute = c.get(Calendar.MINUTE)
                            val seconds = c.get(Calendar.SECOND)

                                var notificacion =  Notification(
                                    titleValue.value,
                                    descriptionValue.value,
                                    ilicitValue.value,
                                    categoryItem.value,
                                    addressValue.value,
                                    departamentItem.value,
                                    provinceValue.value,
                                    cityValue.value,
                                    levelIncidentItem.value,
                                    stateItem.value,
                                    "${day} / ${month} / ${year}",
                                    "${hour}:${minute}:${seconds}",
                                    userName.value.toString(),
                                    userEmail.value.toString()
                                )
                                if(stateItem.value == "PUBLICADO"){
                                    db.collection("notifications")
                                        .add(notificacion)
                                        .addOnSuccessListener { documentReference ->
                                            Log.d("FIREBASE", "DocumentSnapshot added with ID: ${documentReference.id}")
                                            notificacion.savefirestoreID(documentReference.id)
                                            viewModel.insertNotification( notificacion)
                                            navController.navigate("main")
                                        }
                                        .addOnFailureListener { e ->
                                            Log.w("ERROR_FIREBASE", "Error adding document", e)
                                        }

                                }
                                else{
                                    viewModel.insertNotification( notificacion)
                                    navController.navigate("main")
                                }
                        }
                    )
                }
            }
        }
    }
}