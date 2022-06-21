package com.example.samplememorynotes.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.core.data.Note
import com.example.core.repository.NoteRepository
import com.example.core.usecase.AddNote
import com.example.core.usecase.GetAllNotes
import com.example.core.usecase.GetNote
import com.example.core.usecase.RemoveNote
import com.example.samplememorynotes.framework.db.Usecases
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    // repository should not be created in ViewModel TODO
    val repository = NoteRepository(RoomNoteDataSource(application))

    val useCases = Usecases(
        AddNote(repository),
        GetAllNotes(repository),
        RemoveNote(repository),
        GetNote(repository)
    )

    val saved = MutableLiveData<Boolean>()

    fun saveNote(note: Note) {
        coroutineScope.launch {// db repo usecases tasks to be done in bg thread
            useCases.addNote(note)
            saved.postValue(true)
        }
    }
}