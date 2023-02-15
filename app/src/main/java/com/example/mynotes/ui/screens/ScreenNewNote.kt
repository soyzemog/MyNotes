package com.example.mynotes.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


enum class ColorPick(
    val color: Color,
    val nameColor: String
) {
    GREEN(Color.Green, "Green"),
    RED(Color.Red, "Red"),
    YELLOW(Color.Yellow, "Yellow"),
    CYAN(Color.Cyan, "Cyan"),
    MAGENTA(Color.Magenta, "Magenta")
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ScreenNewNote() {

    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    )
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )

    BottomSheetScaffold(
        sheetContent = {
            BottomSheet()
        },
        sheetPeekHeight = 50.dp,
        sheetShape = RoundedCornerShape(16.dp),
        scaffoldState = scaffoldState
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item {
                NoteTitle()
            }

            item {
                NoteSubtitle()
            }

            item {
                TypeNote()
            }

        }
    }
}

@Composable
fun BottomSheet() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(380.dp)
    ) {
        HeaderSheet()
        Row(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 4.dp)) {
            ColorPicker()
        }
        Row(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp)) {
            ImageAndLink()
        }
    }

}


@Composable
fun HeaderSheet() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(MaterialTheme.colors.primary),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Miscellaneous",
            style = MaterialTheme.typography.h6
        )
    }
}


@Composable
fun ColorPicker() {

    val radioOptions = listOf(
        ColorPick.GREEN,
        ColorPick.RED,
        ColorPick.YELLOW,
        ColorPick.CYAN,
        ColorPick.MAGENTA
    )

    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

    Box(
        modifier = Modifier
            .background(
                color = Color.LightGray,
                shape = RoundedCornerShape(16.dp)
            )
            .fillMaxWidth()
            .height(220.dp)
            .padding(start = 20.dp, top = 8.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            // Note that Modifier.selectableGroup() is essential to ensure correct accessibility behavior
            Column(
                modifier = Modifier
                    .selectableGroup()
                    .weight(1f)
            ) {
                radioOptions.forEach { color ->
                    Row(
                        Modifier
                            .selectable(
                                selected = (color == selectedOption),
                                onClick = { onOptionSelected(color) },
                                role = Role.RadioButton
                            )
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (color == selectedOption),
                            onClick = null, // null recommended for accessibility with screenreaders
                            colors = RadioButtonDefaults.colors(color.color)
                        )
                        Text(
                            text = color.nameColor,
                            style = MaterialTheme.typography.body2,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                Text(
                    text = "Pick Color",
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }

}


@Composable
fun ImageAndLink() {

    Box(
        modifier = Modifier
            .background(
                color = Color.LightGray,
                shape = RoundedCornerShape(16.dp)
            )
            .fillMaxWidth()
            .height(90.dp)
            .padding(start = 20.dp, top = 8.dp)
    ) {



    }

}


@Composable
fun NoteTitle() {

    var noteTitle by remember { mutableStateOf(TextFieldValue("")) }


    TextField(
        value = noteTitle,
        onValueChange = { noteTitle = it },
        textStyle = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        ),
        placeholder = { Text(
            text = "Note Title",
            color = Color.LightGray
        ) },
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.padding(4.dp))

    Text(
        text = "Lunes, 10-01-2022 10:10 AM",
        fontSize = 12.sp
    )

}

@Composable
fun NoteSubtitle() {

    var noteSubTitle by remember { mutableStateOf(TextFieldValue("")) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Box(modifier = Modifier
            .height(56.dp)
            .width(10.dp)
            .background(Color.Green, RoundedCornerShape(6.dp)))

        TextField(
            value = noteSubTitle,
            onValueChange = { noteSubTitle = it },
            textStyle = TextStyle(
                fontSize = 16.sp
            ),
            placeholder = { Text(
                text = "Note Subtitle",
                color = Color.LightGray
            ) },
            modifier = Modifier
                .fillMaxWidth()
        )

    }
}

@Composable
fun TypeNote() {

    var note by remember { mutableStateOf(TextFieldValue("")) }

    TextField(
        value = note,
        onValueChange = { note = it },
        textStyle = TextStyle(
            fontSize = 14.sp
        ),
        placeholder = { Text(
            text = "Type note here..",
            color = Color.LightGray
        ) },
        modifier = Modifier
            .fillMaxSize()
            .aspectRatio(1f)
    )
}



@Preview(device = Devices.PIXEL_2)
@Composable
fun Preview() {
    Surface(modifier = Modifier.fillMaxSize()) {

        ScreenNewNote()

    }
}