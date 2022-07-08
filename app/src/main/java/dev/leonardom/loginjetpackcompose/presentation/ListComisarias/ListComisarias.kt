package dev.leonardom.loginjetpackcompose.presentation.ListComisarias

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.paging.compose.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import dev.leonardom.loginjetpackcompose.Retrofit.Data.Data
import dev.leonardom.loginjetpackcompose.presentation.components.ComisariaCard
import dev.leonardom.loginjetpackcompose.viewModel.ComisariasViewModel

@Composable
fun ListComisarias(navController: NavHostController, viewModel:ComisariasViewModel){
    val res = viewModel.pager.collectAsLazyPagingItems()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    )
    {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
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
                    text = "Lista de Comisarias",
                    style = MaterialTheme.typography.h5.copy(
                        color = MaterialTheme.colors.primary
                    )
                )
            }

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 0.5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
            ){
                LazyColumn {
                    items(res) { data ->
                        if (data != null) {
                            ComisariaCard(data)
                        }else{
                            Text(text ="no")
                        }
                    }
                    res.apply {
                        when{
                            loadState.refresh is LoadState.Loading ->{
                                item{
                                    Box(modifier = Modifier
                                        .fillMaxSize()
                                        .padding(16.dp)) {
                                        CircularProgressIndicator(modifier = Modifier
                                            .padding(12.dp)
                                            .align(Alignment.Center))
                                    }
                                }
                            }

                            loadState.append is LoadState.Loading ->{
                                item{
                                    Box(modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)) {
                                        CircularProgressIndicator(modifier = Modifier
                                            .padding(12.dp)
                                            .align(Alignment.Center))
                                    }
                                }
                            }

                            loadState.prepend is LoadState.Loading ->{
                                item{
                                    Box(modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)) {
                                        CircularProgressIndicator(modifier = Modifier
                                            .padding(12.dp)
                                            .align(Alignment.Center))
                                    }
                                }
                            }

                        }
                    }
                }
            }
        }
    }
}