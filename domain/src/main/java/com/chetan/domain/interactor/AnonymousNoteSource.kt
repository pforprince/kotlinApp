package com.chetan.domain.interactor

import com.chetan.domain.servicelocator.NoteServiceLocator
import com.chetan.domain.domainmodel.Note
import com.chetan.domain.domainmodel.Result

class AnonymousNoteSource {
    suspend fun getNotes(locator: NoteServiceLocator):
            Result<Exception, List<Note>> = locator.localAnon.getNotes()

    suspend fun getNoteById(id: String, locator: NoteServiceLocator):
            Result<Exception, Note?> = locator.localAnon.getNote(id)

    suspend fun updateNote(note: Note, locator: NoteServiceLocator):
            Result<Exception, Unit> = locator.localAnon.updateNote(note)


    suspend fun deleteNote(note: Note, locator: NoteServiceLocator):
            Result<Exception, Unit> = locator.localAnon.deleteNote(note)

}