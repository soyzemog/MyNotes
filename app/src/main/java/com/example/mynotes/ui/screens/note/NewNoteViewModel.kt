package com.example.mynotes.ui.screens.note

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynotes.R
import com.example.mynotes.data.domain.Note
import com.example.mynotes.data.repositories.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewNoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    /** init {

        viewModelScope.launch {

           val idNote = savedStateHandle.get<Int>("noteId") ?: 0

            if (idNote != 0) {
                // completo campos (edita nota)
            }

        }

    }  **/


    fun onMiscellaneousOptionsChange(action: MiscellaneousOptionsAction) {
        when (action) {
            is MiscellaneousOptionsAction.OnPickColorClick -> {
                _state.update {
                    it.copy(
                        subtitle = _state.value.subtitle.copy(
                            colorBox = action.color
                        ),
                        miscellaneous = _state.value.miscellaneous.copy(
                            selectedOption = action.color
                        )
                    )
                }
            }
            is MiscellaneousOptionsAction.OnAddLinkClick -> {
                _state.update {
                    it.copy(
                        miscellaneous = _state.value.miscellaneous.copy(
                            showModal = action.showModal
                        )
                    )
                }
            }
            is MiscellaneousOptionsAction.OnTextLinkClick -> {
                _state.update {
                    it.copy(
                        miscellaneous = _state.value.miscellaneous.copy(
                            url = action.text
                        )
                    )
                }
            }
            is MiscellaneousOptionsAction.OnAddLinkToNoteClick -> {
                _state.update {
                    it.copy(
                        url = action.url
                    )
                }
            }
            is MiscellaneousOptionsAction.OnAddImageClick -> {
                _state.update {
                    it.copy(
                        image = action.image
                    )
                }
            }
        }
    }

    fun onNewNoteChange(action: NewNoteAction) {
        when (action) {
            is NewNoteAction.OnTitleText -> {
                _state.update {
                    it.copy(
                        title = _state.value.title.copy(
                            title = action.text
                        )
                    )
                }
            }
            is NewNoteAction.OnSubtitleText -> {
                _state.update {
                    it.copy(
                        subtitle = _state.value.subtitle.copy(
                            subtitle = action.text
                        )
                    )
                }
            }
            is NewNoteAction.OnTypeText -> {
                _state.update {
                    it.copy(
                        typeText = action.text
                    )
                }
            }
        }
    }

    // on below line we are creating a function to open custom chrome tabs.
    fun openTab(context: Context, url: String) {
        // on below line we are creating a variable for
        // package name and specifying package name as
        // package of chrome application.
        val packageName = "com.android.chrome"

        // on below line we are creating a variable for
        // our URL which we have to open in chrome tabs
        /** val URL = "https://www.geeksforgeeks.org" **/

        // on below line we are creating a variable
        // for the activity and initializing it.
        val activity = (context as? Activity)

        // on below line we are creating a variable for
        // our builder and initializing it with
        // custom tabs intent
        val builder = CustomTabsIntent.Builder()

        // on below line we are setting show title
        // to true to display the title for
        // our chrome tabs.
        builder.setShowTitle(true)

        // on below line we are enabling instant
        // app to open if it is available.
        builder.setInstantAppsEnabled(true)

        // on below line we are setting tool bar color for our custom chrome tabs.
        builder.setToolbarColor(ContextCompat.getColor(context, R.color.my_yellow))

        // on below line we are creating a
        // variable to build our builder.
        val customBuilder = builder.build()

        // on below line we are checking if the package name is null or not.
        if (packageName != null) {
            // on below line if package name is not null
            // we are setting package name for our intent.
            customBuilder.intent.setPackage(packageName)

            // on below line we are calling launch url method
            // and passing url to it on below line.
            customBuilder.launchUrl(context, Uri.parse("https://$url"))
        } else {
            // this method will be called if the
            // chrome is not present in user device.
            // in this case we are simply passing URL
            // within intent to open it.
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://$url"))

            // on below line we are calling start
            // activity to start the activity.
            activity?.startActivity(i)
        }
    }

    fun clearUrl() {
        _state.update {
            it.copy(
                url = ""
            )
        }
    }

    fun saveNote() {
        _state.update {
            it.copy(
                note = _state.value.note.copy(
                    id = _state.value.id,
                    title = _state.value.title.title,
                    subtitle = _state.value.subtitle.subtitle,
                    //_state.value.title.date,
                    //_state.value.image,
                    //_state.value.url,
                    //_state.value.subtitle.colorBox
                )
            )
        }

        val TAG = "MyActivity"
        Log.d(TAG, "---------- TITLE :  ${_state.value.title.title.toString()}")

        Log.d(TAG, "---------- SUBTITLE :  ${_state.value.subtitle.subtitle.toString()}")

        viewModelScope.launch {
            noteRepository.insertNote(_state.value.note)
        }
    }


    data class UiState(
        val note: Note = Note(),
        val id: Int? = null,
        val title: TitleNote = TitleNote(),
        val subtitle: SubtitleNote = SubtitleNote(),
        var url: String = "",
        var image: Bitmap? = null,
        var typeText: String = "",
        val miscellaneous: MiscellaneousOptions = MiscellaneousOptions()
    )

}


/**
 * - Esta mal, xq creo un nuevo UiState, en vez de actualizar el estado de
        miscellaneous:
 * _state.value = UiState(
    miscellaneous = MiscellaneousOptions(selectedOption = action.color)
    )
 */

/**
 * solucionado en ' fun onColorPickChange(action: MiscellaneousOptionsAction) {} '
 * - lo mismo q arriba, creaba nuevamente tanto SubtitleNote como MiscellaneousOptions,
 *  x lo q me volvia a setear algunos valores a x defecto y se perdian los estados
 *  q seteaba
 * subtitle = SubtitleNote(
    colorBox = action.color
    ),
    miscellaneous = MiscellaneousOptions(selectedOption = action.color)
 **/