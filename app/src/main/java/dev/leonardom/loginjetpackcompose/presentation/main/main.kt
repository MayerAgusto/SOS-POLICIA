package dev.leonardom.loginjetpackcompose.presentation.main

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import dev.leonardom.loginjetpackcompose.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import dev.leonardom.loginjetpackcompose.DataStore.DataStore
import dev.leonardom.loginjetpackcompose.presentation.components.RoundedButton

@Composable
fun Main(navController: NavHostController, dataStore: DataStore) {
    val context = LocalContext.current
    val dataStore = DataStore(context)
    val userName = dataStore.getName.collectAsState(initial = "").value

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
                        navController.navigate("login")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back Icon",
                        tint = MaterialTheme.colors.primary
                    )
                }
                Column() {
                    Text(
                        text = "Alerta vecinal App",
                        style = MaterialTheme.typography.h5.copy(
                            color = MaterialTheme.colors.primary
                        )
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "Bienvenido ${userName}",
                        style = MaterialTheme.typography.h4.copy(
                            color = MaterialTheme.colors.primary
                        )
                    )
                    Text(
                        text = "MENU PRINCIPAL ",
                        style = MaterialTheme.typography.h6.copy(
                            color = MaterialTheme.colors.primary
                        )
                    )
                }

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(modifier = Modifier.width(140.dp)
                ) {
                    val createPinter = painterResource(id = R.drawable.create)
                    val createImageDescription = "En esta seccion se crea un nuevo incidente"
                    val createTitle = "Crear incidente"

                    ImageCard(painter = createPinter,
                        contentDescription =createImageDescription ,
                        title = createTitle,
                        onClick = { navController.navigate("create")}
                    )
                }

                Box(modifier = Modifier.width(140.dp)
                ) {
                    val viewPinter = painterResource(id = R.drawable.search)
                    val viewImageDescription = "En esta seccion se visualiza los incidentes"
                    val viewTitle = "Mis incidente"
                    ImageCard(painter = viewPinter,
                        contentDescription =viewImageDescription,
                        title = viewTitle, onClick = {
                            navController.navigate("myIncidents")
                        })
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(modifier = Modifier.width(140.dp)
                ) {
                    val locationPinter = painterResource(id = R.drawable.local)
                    val locationImageDescription = "En esta seccion se filtran los incidentes segun la localidad"
                    val locationTitle = "Ver incidentes en mi zona"
                    ImageCard(painter = locationPinter,
                        contentDescription =locationImageDescription ,
                        title = locationTitle,
                        onClick = {})
                }
                Box(modifier = Modifier.width(140.dp)
                ) {
                    val policePinter = painterResource(id = R.drawable.police)
                    val policeImageDescription = "En esta seccion se filtran los incidentes segun la localidad"
                    val policeTitle = "Ver comisarias"

                    ImageCard(painter = policePinter,
                        contentDescription =policeImageDescription ,
                        title = policeTitle,
                        onClick = {})
                }

            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)

            ) {
                RoundedButton(
                    text = "Salir",
                    displayProgressBar = false,
                    onClick = {
                        navController.navigate("main")
                    }
                )
            }

        }


    }
}

@Composable
fun ImageCard(
    painter: Painter,
    contentDescription: String,
    title: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
){
    Card(
       modifier = modifier.fillMaxWidth().clickable(onClick= onClick),
       shape = RoundedCornerShape(15.dp),
       elevation = 5.dp,
    ){
        Box(modifier = Modifier.height(200.dp)){
            Image(
              painter = painter,
                contentDescription = contentDescription,
              contentScale = ContentScale.Crop
            )
            Box(modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            MaterialTheme.colors.secondaryVariant
                        ),
                        startY = 260f
                    )
                )
            ) {

            }
            Box(
                modifier= Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(title,
                    style = TextStyle(color = Color.White, fontSize = 15.sp)
                    )
            }
        }
    }
}