package com.example.mynotes.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun CustomDialog(
    show: Boolean,
    onDismiss: () -> Unit
) {

    var url by remember { mutableStateOf(TextFieldValue("")) }

    if (show) {

        Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        MaterialTheme.colors.onSecondary,
                        RoundedCornerShape(16.dp)
                    )
                    .padding(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconAddLink(Color.White)
                }
                
                Spacer(modifier = Modifier.padding(12.dp))
                
                TextField(
                    value = url,
                    onValueChange = { url = it },
                    placeholder = {
                        Text(
                            text = "Enter URL",
                            color = Color.LightGray
                        )},
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.padding(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        onClick = onDismiss,
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = MaterialTheme.colors.primaryVariant
                        )
                    ) {
                        Text(text = "CANCEL")
                    }
                    Spacer(modifier = Modifier.padding(8.dp))
                    TextButton(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = MaterialTheme.colors.primaryVariant
                        )
                    ) {
                        Text(text = "ADD")
                    }
                }

            }
        }

    }

}