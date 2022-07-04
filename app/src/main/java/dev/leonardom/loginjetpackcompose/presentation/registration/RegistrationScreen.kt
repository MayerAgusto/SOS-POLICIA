package dev.leonardom.loginjetpackcompose.presentation.registration

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore
import dev.leonardom.loginjetpackcompose.DataStore.DataStore
import dev.leonardom.loginjetpackcompose.presentation.components.AutoComplete
import dev.leonardom.loginjetpackcompose.presentation.components.RoundedButton
import dev.leonardom.loginjetpackcompose.presentation.components.TransparentTextField
import kotlinx.coroutines.launch


@Composable
fun RegistrationScreen(
    navController: NavHostController,
    dataStore: DataStore,
    db: FirebaseFirestore
) {

    val nameValue = remember { mutableStateOf("") }
    val emailValue = remember { mutableStateOf("") }
    val passwordValue = remember { mutableStateOf("") }
    val departamentList = "Amazonas, Ancash, Apurimac, Arequipa, Ayacucho, Cajamarca, Callao, Cusco, Huancavelica, Huanuco, Ica, Junín, La Libertad, Lambayeque, Lima, Loreto, Madre de Dios, Moquegua, Pasco, Piura, Puno, San Martín, Tacna, Tumbes, Ucayali"
    val departamentTitle  ="Ingrese su departamento"
    var departamentItem = remember { mutableStateOf("") }
    val provinceValue = remember { mutableStateOf("") }
    val cityValue = remember { mutableStateOf("") }

    var passwordVisibility by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()


    Box(
      modifier = Modifier
          .fillMaxWidth()
          .background(MaterialTheme.colors.background)
    ){
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                IconButton(
                    onClick = {
                        navController.navigate("login")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back Icon",
                        tint = MaterialTheme.colors.primary
                    )
                }

                Text(
                    text = "Create una cuenta",
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
            ) {
                TransparentTextField(
                    textFieldValue = nameValue,
                    textLabel = "Nombre",
                    keyboardType = KeyboardType.Text,
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    imeAction = ImeAction.Next
                )

                TransparentTextField(
                    textFieldValue = emailValue,
                    textLabel = "Correo Electronico",
                    keyboardType = KeyboardType.Email,
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    ),
                    imeAction = ImeAction.Next
                )
                TransparentTextField(
                    textFieldValue = passwordValue,
                    textLabel = "Contraseña",
                    keyboardType = KeyboardType.Password,
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    imeAction = ImeAction.Next,
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                passwordVisibility = !passwordVisibility
                            }
                        ) {
                            Icon(
                                imageVector = if (passwordVisibility) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = "Toggle Password Icon"
                            )
                        }
                    },
                    visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation()
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
                    textLabel = "Ciudad",
                    keyboardType = KeyboardType.Text,
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    imeAction = ImeAction.Next
                )

                Spacer(modifier = Modifier.height(16.dp))

                RoundedButton(
                    text = "Crear cuenta",
                    displayProgressBar = false,
                    onClick = {
                            val user = hashMapOf(
                                "nameValue" to nameValue.value,
                                "departament" to departamentItem.value,
                                "province" to provinceValue.value,
                                "city" to cityValue.value,
                                "email" to emailValue.value,
                                "password" to passwordValue.value,
                            )

                            db.collection("users")
                                .add(user).addOnSuccessListener { documentReference ->
                                    Log.d("FIREBASE", "DocumentSnapshot added with ID: ${documentReference.id}")
                                    scope.launch {
                                        dataStore.saveName(nameValue.value)
                                        dataStore.saveDepartment(departamentItem.value)
                                        dataStore.saveProvince(provinceValue.value)
                                        dataStore.saveCity(cityValue.value)
                                        dataStore.saveEmail(emailValue.value)
                                        dataStore.savePassword(passwordValue.value)
                                        navController.navigate("login")
                                    }
                                }
                                .addOnFailureListener { e ->
                                    Log.w("ERROR_FIREBASE", "Error adding document", e)
                                }
                    }
                )

                ClickableText(

                    text = buildAnnotatedString {
                        append("Ya tienes una cuenta !! ")

                        withStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colors.primary,
                                fontWeight = FontWeight.Bold
                            )
                        ){
                            append("Inicia sesion")
                        }
                    },
                    onClick = {

                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){

                    Divider(
                        modifier = Modifier.width(24.dp),
                        thickness = 1.dp,
                        color = Color.Gray
                    )
                }

            }

            Spacer(modifier = Modifier.height(16.dp))


        }
    }
}