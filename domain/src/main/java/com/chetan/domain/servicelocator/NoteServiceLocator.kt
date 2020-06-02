package com.chetan.domain.servicelocator

import com.chetan.domain.repository.ILocalNoteRepository
import com.chetan.domain.repository.IPublicNoteRepository
import com.chetan.domain.repository.IRemoteNoteRepository
import com.chetan.domain.repository.ITransactionRepository

class NoteServiceLocator(val localAnon: ILocalNoteRepository,
                         val remoteReg: IRemoteNoteRepository,
                         val transactionReg: ITransactionRepository,
                         val remotePublic: IPublicNoteRepository)