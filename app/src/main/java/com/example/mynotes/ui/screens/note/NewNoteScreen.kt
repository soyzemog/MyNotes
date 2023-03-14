package com.example.mynotes.ui.screens.note


import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.RadioButtonChecked
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mynotes.ui.component.CustomDialog
import com.example.mynotes.ui.component.IconAddLink
import com.example.mynotes.ui.component.TextDate


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
fun NewNoteScreen() {

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
            .height(236.dp)
            .background(MaterialTheme.colors.onBackground)
    ) {
        HeaderSheet()
        Spacer(modifier = Modifier.padding(8.dp))
        ColorPicker()
        AddImage()
        AddLink()
    }

}


@Composable
fun HeaderSheet() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Miscellaneous",
            style = MaterialTheme.typography.h6,
            color = Color.White
        )
    }
}


/** @Composable
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
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {

                // Note that Modifier.selectableGroup() is essential to ensure correct accessibility behavior
                Column(
                    modifier = Modifier
                        .selectableGroup()
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

            }

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {
                Text(
                    text = "Pick Color",
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }

} **/

@Composable
fun ColorPicker() {

    val radioOptions = listOf(
        ColorPick.GREEN,
        ColorPick.RED,
        ColorPick.YELLOW,
        ColorPick.CYAN,
        ColorPick.MAGENTA
    )

    var selectedItem by remember { mutableStateOf(radioOptions[0]) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Column() {
            Row(modifier = Modifier.selectableGroup()) {
                radioOptions.forEach { color ->
                    Icon(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .selectable(
                                selected = (selectedItem == color),
                                onClick = { selectedItem = color },
                                role = Role.RadioButton
                            )
                            .size(40.dp)
                        ,
                        imageVector = if (selectedItem == color)
                            Icons.Outlined.CheckCircle else
                            Icons.Outlined.RadioButtonChecked,
                        contentDescription = color.nameColor,
                        tint = color.color
                    )
                }
            }
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            Text(
                text = "Pick Color",
                fontSize = 14.sp,
                textDecoration = TextDecoration.Underline,
                color = MaterialTheme.colors.onPrimary
            )
        }
    }

}


/** @Composable
fun ImageAndLink() {

    Box(
        modifier = Modifier
            .background(
                color = Color.LightGray,
                shape = RoundedCornerShape(16.dp)
            )
            .fillMaxWidth()
            .height(90.dp)
    ) {

        Row(modifier = Modifier.fillMaxWidth()) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {
                AddImage()
            }

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {
                AddLink()
            }

        }

    }

} **/


@Composable
fun AddImage() {

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }

    val launcher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            imageUri = uri
        }

    imageUri?.let {
        if (Build.VERSION.SDK_INT < 28) {
            bitmap.value = MediaStore.Images
                .Media.getBitmap(context.contentResolver, it)
        } else {
            val source = ImageDecoder.createSource(context.contentResolver, it)
            bitmap.value = ImageDecoder.decodeBitmap(source)
        }
    }

    /** bitmap.value?.let { btm ->
        Image(
            bitmap = btm.asImageBitmap(),
            contentDescription = null,
            modifier = Modifier
                .size(400.dp)
                .padding(20.dp)
        )
    } **/

    Row(
        modifier = Modifier
            .padding(start = 16.dp, top = 20.dp)
            .clickable { launcher.launch("image/*") },
        verticalAlignment = Alignment.CenterVertically
    ) {
        
        Icon(
            imageVector = Icons.Default.AddPhotoAlternate,
            contentDescription = "add image",
            modifier = Modifier.size(36.dp),
            tint = MaterialTheme.colors.onPrimary
        )

        Spacer(modifier = Modifier.padding(6.dp))
        
        Text(
            text = "Add Image",
            fontSize = 14.sp,
            color = MaterialTheme.colors.onPrimary
        )
        
    }
}


@Composable
fun AddLink() {

    var showModal by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(start = 16.dp, top = 20.dp)
            .clickable { showModal = true },
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconAddLink(MaterialTheme.colors.onPrimary)
    }

    CustomDialog(showModal) {
        showModal = false
    }

}


@Composable
fun NoteTitle() {

    var noteTitle by remember { mutableStateOf("") }
    
    BasicTextField(
        value = noteTitle,
        onValueChange = { newText ->
            noteTitle = newText
        },
        textStyle = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        ),
        cursorBrush = SolidColor(MaterialTheme.colors.primaryVariant),
        decorationBox = { innerTextField ->
            if (noteTitle.isEmpty()) {
                Text(
                    text = "Note Title",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colors.onPrimary
                )
            }
            innerTextField()
        },
        maxLines = 2
    )

    Spacer(modifier = Modifier.padding(4.dp))

    TextDate()

}

@Composable
fun NoteSubtitle() {

    var noteSubTitle by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Box(modifier = Modifier
            .height(56.dp)
            .width(10.dp)
            .background(Color.Green, RoundedCornerShape(6.dp))
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(start = 8.dp),
            verticalArrangement = Arrangement.Center
        ) {

            BasicTextField(
                value = noteSubTitle,
                onValueChange = { newText ->
                    noteSubTitle = newText
                },
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                ),
                cursorBrush = SolidColor(MaterialTheme.colors.primaryVariant),
                decorationBox = { innerTextField ->
                    if (noteSubTitle.isEmpty()) {
                        Text(
                            text = "Note Subtitle",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            color = MaterialTheme.colors.onPrimary
                        )
                    }
                    innerTextField()
                },
                maxLines = 2
            )

        }
    }

    /** var noteSubTitle by remember { mutableStateOf(TextFieldValue("")) }

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

    } **/
}

@Composable
fun TypeNote() {

    var note by remember { mutableStateOf("") }

    BasicTextField(
        value = note,
        onValueChange = { newText ->
            note = newText
        },
        textStyle = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        ),
        cursorBrush = SolidColor(MaterialTheme.colors.primaryVariant),
        decorationBox = { innerTextField ->
            if (note.isEmpty()) {
                Text(
                    text = "Type note here..",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colors.onPrimary
                )
            }
            innerTextField()
        }
    )
}

/** @Preview(device = Devices.PIXEL_2)
@Composable
fun Preview() {
    Surface(modifier = Modifier.fillMaxSize()) {

        NewNoteScreen()

    }
} **/