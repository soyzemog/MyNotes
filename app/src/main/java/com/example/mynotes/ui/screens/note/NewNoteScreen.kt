package com.example.mynotes.ui.screens.note


import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mynotes.ui.component.CustomDialog
import com.example.mynotes.ui.component.IconAddLink
import com.example.mynotes.ui.component.TextDate


enum class ColorPick(
    val color: Color,
    val nameColor: String
) {
    GREEN(Color.Green, "green"),
    RED(Color.Red, "red"),
    YELLOW(Color.Yellow, "yellow"),
    CYAN(Color.Cyan, "cyan"),
    MAGENTA(Color.Magenta, "magenta")
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewNoteScreen(
    viewModel: NewNoteViewModel = hiltViewModel()
) {

    val ctx = LocalContext.current

    val state by viewModel.state.collectAsState()

    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    )
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )

    BottomSheetScaffold(
        sheetContent = {
            BottomSheet(
                miscellaneous = state.miscellaneous,
                onAction = { viewModel.onMiscellaneousOptionsChange(it) }
            )
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
                NoteTitle(
                    noteTitle = state.title,
                    onAction = { viewModel.onNewNoteChange(it) }
                )
            }

            item {
                NoteSubtitle(
                    noteSubTitle = state.subtitle,
                    onAction = { viewModel.onNewNoteChange(it) }
                )
            }

            if (state.url.isNotEmpty()) {
                item {
                    RowLink(
                        url = state.url,
                        onDelete = { viewModel.clearUrl() },
                        onAction = { viewModel.openTab(ctx, state.url) }
                    )
                }
            }

            if (state.image != null) {
                item {
                    ImageFromGallery(state.image!!)
                }
            }

            item {
                TypeText(
                    typeText = state.typeText,
                    onAction = { viewModel.onNewNoteChange(it) }
                )
            }

        }
    }
}

@Composable
fun BottomSheet(
    miscellaneous: MiscellaneousOptions,
    onAction: (MiscellaneousOptionsAction) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(236.dp)
            .background(MaterialTheme.colors.onBackground)
    ) {
        HeaderSheet()
        Spacer(modifier = Modifier.padding(8.dp))
        ColorPicker(
            miscellaneous = miscellaneous,
            onAction = onAction
        )
        AddImage(onAction = onAction)
        AddLink(miscellaneous = miscellaneous, onAction = onAction)
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


@Composable
fun ColorPicker(
    miscellaneous: MiscellaneousOptions,
    onAction: (MiscellaneousOptionsAction) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Column() {
            Row(modifier = Modifier.selectableGroup()) {
                miscellaneous.radioOptions.forEach { color ->
                    Icon(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .selectable(
                                selected = (miscellaneous.selectedOption == color),
                                onClick = {
                                    onAction(
                                        MiscellaneousOptionsAction.OnPickColorClick(
                                            color
                                        )
                                    )
                                },
                                role = Role.RadioButton
                            )
                            .size(40.dp)
                        ,
                        imageVector = if (miscellaneous.selectedOption == color)
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


@Composable
fun AddImage(onAction: (MiscellaneousOptionsAction) -> Unit) {

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    // val bitmap = remember { mutableStateOf<Bitmap?>(null) }

    val launcher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            imageUri = uri
        }

    imageUri?.let {
        if (Build.VERSION.SDK_INT < 28) {
            /** bitmap.value = MediaStore.Images
                .Media.getBitmap(context.contentResolver, it) **/
            // le digo al viewmodel q cambie el estado de image con la imagen seleccionada
            onAction(
                MiscellaneousOptionsAction.OnAddImageClick(
                    MediaStore.Images.Media.getBitmap(
                        context.contentResolver, it
                    )
                )
            )
        } else {
            val source = ImageDecoder.createSource(context.contentResolver, it)
            /** bitmap.value = ImageDecoder.decodeBitmap(source) **/
            onAction(
                MiscellaneousOptionsAction.OnAddImageClick(
                    ImageDecoder.decodeBitmap(source)
                )
            )
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
fun AddLink(
    miscellaneous: MiscellaneousOptions,
    onAction: (MiscellaneousOptionsAction) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(start = 16.dp, top = 20.dp)
            .clickable { onAction(MiscellaneousOptionsAction.OnAddLinkClick(true)) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconAddLink(MaterialTheme.colors.onPrimary)
    }

    CustomDialog(
        show = miscellaneous.showModal,
        url = miscellaneous.url,
        onAction = onAction
    ) {
        onAction(MiscellaneousOptionsAction.OnAddLinkClick(false))
    }

}


@Composable
fun NoteTitle(
    noteTitle: TitleNote,
    onAction: (NewNoteAction) -> Unit
) {
    BasicTextField(
        value = noteTitle.title,
        onValueChange = { newText ->
            onAction(NewNoteAction.OnTitleText(newText))
        },
        textStyle = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        ),
        cursorBrush = SolidColor(MaterialTheme.colors.primaryVariant),
        decorationBox = { innerTextField ->
            if (noteTitle.title.isEmpty()) {
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

    TextDate(noteTitle.date)

}

@Composable
fun NoteSubtitle(
    noteSubTitle: SubtitleNote,
    onAction: (NewNoteAction) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Box(modifier = Modifier
            .height(56.dp)
            .width(10.dp)
            .background(noteSubTitle.colorBox.color, RoundedCornerShape(6.dp))
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(start = 8.dp),
            verticalArrangement = Arrangement.Center
        ) {

            BasicTextField(
                value = noteSubTitle.subtitle,
                onValueChange = { newText ->
                    onAction(NewNoteAction.OnSubtitleText(newText))
                },
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                ),
                cursorBrush = SolidColor(MaterialTheme.colors.primaryVariant),
                decorationBox = { innerTextField ->
                    if (noteSubTitle.subtitle.isEmpty()) {
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
}


@Composable
fun RowLink(
    url: String,
    onDelete: () -> Unit,
    onAction: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextButton(
            onClick = onAction,
            colors = ButtonDefaults.textButtonColors(
                contentColor = MaterialTheme.colors.primaryVariant
            )
        ) {
            Text(
                text = url,
                fontSize = 16.sp,
                textDecoration = TextDecoration.Underline,
                maxLines = 2
            )
        }
        IconButton(onClick = onDelete) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete",
                tint = Color.Red,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}


@Composable
fun ImageFromGallery(image: Bitmap) {
    Image(
        bitmap = image.asImageBitmap(),
        contentDescription = null
    )
}


@Composable
fun TypeText(
    typeText: String,
    onAction: (NewNoteAction) -> Unit
) {
    BasicTextField(
        value = typeText,
        onValueChange = { newText ->
            onAction(NewNoteAction.OnTypeText(newText))
        },
        textStyle = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        ),
        cursorBrush = SolidColor(MaterialTheme.colors.primaryVariant),
        decorationBox = { innerTextField ->
            if (typeText.isEmpty()) {
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