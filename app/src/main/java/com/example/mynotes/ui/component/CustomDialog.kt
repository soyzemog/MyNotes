package com.example.mynotes.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun CustomDialog(
    show: Boolean,
    onDismiss: () -> Unit
) {

    var url by remember { mutableStateOf("") }

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

                BasicTextField(
                    value = url,
                    onValueChange = { newText ->
                        url = newText
                    },
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ),
                    cursorBrush = SolidColor(MaterialTheme.colors.primaryVariant),
                    decorationBox = { innerTextField ->
                        if (url.isEmpty()) {
                            Text(
                                text = "Enter URL",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal,
                                color = MaterialTheme.colors.onPrimary
                            )
                        }
                        innerTextField()
                    },
                    singleLine = true
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