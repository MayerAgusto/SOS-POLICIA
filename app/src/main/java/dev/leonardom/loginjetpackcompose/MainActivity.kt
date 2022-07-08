package dev.leonardom.loginjetpackcompose

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dev.leonardom.loginjetpackcompose.DataStore.DataStore
import dev.leonardom.loginjetpackcompose.RoomDatabase.Model.Notification
import dev.leonardom.loginjetpackcompose.presentation.CreateFormNotification.FormNotification
import dev.leonardom.loginjetpackcompose.presentation.ListComisarias.ListComisarias
import dev.leonardom.loginjetpackcompose.presentation.MyIncidents.MyIncidents
import dev.leonardom.loginjetpackcompose.presentation.login.LoginScreen
import dev.leonardom.loginjetpackcompose.presentation.main.Main
import dev.leonardom.loginjetpackcompose.presentation.registration.RegistrationScreen
import dev.leonardom.loginjetpackcompose.ui.theme.LoginJetpackComposeTheme
import dev.leonardom.loginjetpackcompose.viewModel.ComisariasViewModel
import dev.leonardom.loginjetpackcompose.viewModel.MainViewModel

class MainActivity : ComponentActivity() {
    private val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginJetpackComposeTheme{
                val owner = LocalViewModelStoreOwner.current
                owner?.let{
                    val viewModel: MainViewModel = viewModel(
                        it,
                        "MainViewModel",
                        MainViewModelFactory(
                            LocalContext.current.applicationContext as Application
                        )
                    )
                    ScreenSetup(viewModel, db)
                }

            }
        }
    }

    @Composable
    fun ScreenSetup(viewModel: MainViewModel, db: FirebaseFirestore){
        val navController = rememberNavController()
        val context = LocalContext.current
        val dataStore = DataStore(context)
        var startApp = ""
        val userEmail = dataStore.getEmail.collectAsState(initial = "")
        val userPassword = dataStore.getPassword.collectAsState(initial = "")
        //TODO(LISTA QUE CONTIENE TODAS LAS NOTIFICACIONES EN UNA LISTA)
        val allNotifications by viewModel.allNotifications!!.observeAsState(listOf())
        //TODO(VARIABLE DONDE ALMACENA LA NOTIFICACION BUSCADA MEDIANTE ID)
        val searchResult by viewModel.searchResult!!.observeAsState(Notification())

        if(userEmail.value!! == "" && userPassword.value!! == ""){
            startApp = "login"
        }else{
            startApp = "main"
        }

        NavHost(navController = navController, startDestination = startApp){
            composable("login"){
                LoginScreen(navController, dataStore, db)
            }
            composable("register"){
                RegistrationScreen(navController, dataStore, db)
            }
            composable("main"){
                Main(navController, dataStore)
            }
            composable("create"){
                FormNotification(navController,dataStore, viewModel, db)
            }
            composable("myIncidents"){
                MyIncidents(navController, allNotifications, viewModel, searchResult)
            }
            composable("ListComisarias"){
                ListComisarias(navController,ComisariasViewModel())
            }

        }
    }
}

class MainViewModelFactory(
    val application: Application
): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(application) as T
    }
}