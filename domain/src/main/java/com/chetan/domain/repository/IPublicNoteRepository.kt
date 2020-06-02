package com.chetan.domain.repository

import com.chetan.domain.domainmodel.Note
import com.chetan.domain.domainmodel.Result

interface IPublicNoteRepository {
    suspend fun getNotes():Result<Exception, List<Note>>

    suspend fun getNote(id: String): Result<Exception, Note?>

    suspend fun deleteNote(note: Note): Result<Exception, Unit>

    suspend fun updateNote(note: Note):Result<Exception, Unit>
}