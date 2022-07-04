package dev.leonardom.loginjetpackcompose.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun AutoComplete(
    selectedItem: MutableState<String>,
    data: String,
text: String,
){
    var expanded by remember { mutableStateOf(false)}
    val list = data.split(",").map { it.trim().uppercase() }
    val icon = if (expanded){
        Icons.Filled.KeyboardArrowUp
    }else{
        Icons.Filled.KeyboardArrowDown
    }

    Column{
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = selectedItem.value,
            enabled = false,
            onValueChange = {selectedItem.value = it},
            label = { Text(text = text)},
            trailingIcon = {
                Icon(icon, "",Modifier.clickable { expanded = !expanded })
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth(),
        ) {
            list.forEach {
                    label ->
                DropdownMenuItem(onClick = {
                    selectedItem.value = label
                    expanded = false
                }) {
                    Text(text = label, color = Color.Black)
                }
            }

        }
    }


}