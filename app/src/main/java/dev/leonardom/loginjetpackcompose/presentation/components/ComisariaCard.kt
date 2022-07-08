package dev.leonardom.loginjetpackcompose.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import dev.leonardom.loginjetpackcompose.Retrofit.Data.Data

@Composable
fun ComisariaCard(data: Data){
    Surface(shape = RoundedCornerShape(6.dp),
        elevation = 4.dp,
        modifier = Modifier.padding(8.dp)) {
        Column(modifier = Modifier.padding(16.dp),
        ) {
            val modifier = Modifier
                .height(150.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(6.dp))
            val painter = rememberAsyncImagePainter(model = data?.image)
            val estado = painter.state
            Image(painter = painter,
                contentDescription =null,
                modifier = modifier,
                contentScale = ContentScale.Crop,
            )
            if(estado is AsyncImagePainter.State.Loading){
                CircularProgressIndicator()
            }

            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly) {
                Column() {
                    Text(
                        buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold,color = Color.Black)) {
                                append("NOMBRE123 : ")
                            }

                            append(data?.nombre.toString())

                        }
                        ,color = Color.Black,style = MaterialTheme.typography.h6
                    )
                    Text(
                        buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold,color = Color.Black)) {
                                append("DEPARTAMENTO : ")
                            }
                            append(data?.departamento.toString())
                        }
                        ,color = Color.Black,style = MaterialTheme.typography.h6
                    )
                    Text(
                        buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold,color = Color.Black)) {
                                append("PROVINCIA : ")
                            }
                            append(data?.provincia.toString())
                        }
                        ,color = Color.Black,style = MaterialTheme.typography.h6
                    )
                    Text(
                        buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold,color = Color.Black)) {
                                append("DISTRITO : ")
                            }
                            append(data?.distrito.toString())
                        }
                        ,color = Color.Black,style = MaterialTheme.typography.h6
                    )
                    Text(
                        buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold,color = Color.Black)) {
                                append("DIVISIÓN POLICIAL : ")
                            }
                            append( data?.divpol_divopus.toString())
                        }
                        ,color = Color.Black,style = MaterialTheme.typography.h6
                    )
                    Text(
                        buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold,color = Color.Black)) {
                                append("TIPO DE COMISARIA : ")
                            }
                            append( data?.tipocomisaria.toString())
                        }
                        ,color = Color.Black,style = MaterialTheme.typography.h6
                    )
                    Text(
                        buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold,color = Color.Black)) {
                                append("LATITUD : ")
                            }
                            append(data?.latitud.toString())
                        }
                        ,color = Color.Black,style = MaterialTheme.typography.h6
                    )
                    Text(
                        buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold,color = Color.Black)) {
                                append("LONGITUD : ")
                            }
                            append(data?.longitud.toString())
                        }
                        ,color = Color.Black,style = MaterialTheme.typography.h6
                    )

                }
            }

        }
    }
}