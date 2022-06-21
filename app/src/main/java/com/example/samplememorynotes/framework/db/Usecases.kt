package com.example.samplememorynotes.framework.db

import com.example.core.data.Note
import com.example.core.usecase.AddNote
import com.example.core.usecase.GetAllNotes
import com.example.core.usecase.GetNote
import com.example.core.usecase.RemoveNote

class Usecases(
  val addNote: AddNote,
  val getAllNotes: GetAllNotes,
  val removeNote: RemoveNote,
  val getNote: GetNote
)

