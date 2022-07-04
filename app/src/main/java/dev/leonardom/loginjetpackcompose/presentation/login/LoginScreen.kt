package dev.leonardom.loginjetpackcompose.presentation.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.google.firebase.firestore.FirebaseFirestore
import dev.leonardom.loginjetpackcompose.DataStore.DataStore
import dev.leonardom.loginjetpackcompose.R
import dev.leonardom.loginjetpackcompose.presentation.components.RoundedButton
import dev.leonardom.loginjetpackcompose.presentation.components.TransparentTextField

@Composable
fun LoginScreen(navController: NavHostController, dataStore: DataStore, db: FirebaseFirestore) {

    val emailValue = rememberSaveable{ mutableStateOf("") }
    val passwordValue = rememberSaveable{ mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    val userEmail = dataStore.getEmail.collectAsState(initial = "")
    val userPassword = dataStore.getPassword.collectAsState(initial = "")

    if(userEmail.value  !== ""){
        emailValue.value = userEmail.value.toString()
    }
    if(userPassword.value !== ""){
        passwordValue.value = userPassword.value.toString()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ){
        Image(
            painter = painterResource(id = R.drawable.logotipo),
            contentDescription = "Login Image",
            contentScale = ContentScale.Inside
        )

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ){
            ConstraintLayout {

                val (surface, fab) = createRefs()

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .constrainAs(surface) {
                            bottom.linkTo(parent.bottom)
                        },
                    color = Color.White,
                    shape = RoundedCornerShape(
                        topStartPercent = 8,
                        topEndPercent = 8
                    )
                ){
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ){
                        Text(
                            text = "Alerta vecinal App",
                            style = MaterialTheme.typography.h4.copy(
                                fontWeight = FontWeight.Medium
                            )
                        )

                        Text(
                            text = "Inicio de sesion",
                            style = MaterialTheme.typography.h5.copy(
                                color = MaterialTheme.colors.primary
                            )
                        )

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ){
                            TransparentTextField(
                                textFieldValue = emailValue,
                                textLabel = "Correo Electronico",
                                keyboardType = KeyboardType.Email,
                                keyboardActions = KeyboardActions(
                                    onNext = {
                                        focusManager.moveFocus(FocusDirection.Down)
                                    }
                                ),
                                imeAction = ImeAction.Next
                            )

                            TransparentTextField(
                                textFieldValue = passwordValue,
                                textLabel = "Contraseña",
                                keyboardType = KeyboardType.Password,
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        focusManager.clearFocus()

                                        //TODO("LOGIN")
                                    }
                                ),
                                imeAction = ImeAction.Done,
                                trailingIcon = {
                                    IconButton(
                                        onClick = {
                                            passwordVisibility = !passwordVisibility
                                        }
                                    ) {
                                        Icon(
                                            imageVector = if(passwordVisibility) {
                                                Icons.Default.Visibility
                                            } else {
                                                Icons.Default.VisibilityOff
                                            },
                                            contentDescription = "Toggle Password Icon"
                                        )
                                    }
                                },
                                visualTransformation = if(passwordVisibility) {
                                    VisualTransformation.None
                                } else {
                                    PasswordVisualTransformation()
                                }
                            )

                        }

                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            val context = LocalContext.current

                            RoundedButton(
                                text = "Iniciar sesion",
                                displayProgressBar = false,
                                onClick = {
                                    db.collection("users")
                                        .whereEqualTo("email", emailValue.value)
                                        .whereEqualTo("password", passwordValue.value)
                                        .get()
                                        .addOnSuccessListener { documents ->
                                            for (document in documents) {
                                                Log.d("ID_DOCUMENTS", "${document.id} => ${document.data}")
                                                if(document.id != ""){
                                                    navController.navigate("main")
                                                }
                                            }
                                        }
                                        .addOnFailureListener { exception ->
                                            Log.w("ERROR", "Error getting documents: ", exception)
                                            Toast.makeText(context,"Datos no validos",Toast.LENGTH_SHORT).show()
                                        }

                                }
                            )

                            ClickableText(

                                text = buildAnnotatedString {
                                    append("No tienes una cuenta, ")

                                    withStyle(
                                        style = SpanStyle(
                                            color = MaterialTheme.colors.primary,
                                            fontWeight = FontWeight.Bold
                                        )
                                    ){
                                        append("Registrate !!")
                                    }
                                }

                            ){
                                navController.navigate("register")
                            }
                        }
                    }
                }

                FloatingActionButton(
                    modifier = Modifier
                        .size(72.dp)
                        .constrainAs(fab) {
                            top.linkTo(surface.top, margin = (-36).dp)
                            end.linkTo(surface.end, margin = 36.dp)
                        },
                    backgroundColor = MaterialTheme.colors.primary,
                    onClick = {}
                ) {
                    Icon(
                        modifier = Modifier.size(42.dp),
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Forward Icon",
                        tint = Color.White
                    )
                }
            }
        }
    }
}














