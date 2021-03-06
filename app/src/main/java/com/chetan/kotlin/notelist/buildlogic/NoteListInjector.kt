package com.chetan.kotlin.notelist.buildlogic

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.FirebaseApp
import com.chetan.data.auth.FirebaseAuthRepositoryImpl
import com.chetan.data.note.anonymous.AnonymousNoteDao
import com.chetan.data.note.anonymous.AnonymousNoteDatabase
import com.chetan.data.note.anonymous.RoomLocalAnonymousRepositoryImpl
import com.chetan.data.note.public.FirestoreRemoteNoteImpl
import com.chetan.data.note.registered.*
import com.chetan.data.transaction.RoomRegisteredTransactionDatabase
import com.chetan.data.transaction.RoomTransactionRepositoryImpl
import com.chetan.domain.DispatcherProvider
import com.chetan.domain.servicelocator.NoteServiceLocator
import com.chetan.domain.servicelocator.UserServiceLocator
import com.chetan.domain.interactor.AnonymousNoteSource
import com.chetan.domain.interactor.AuthSource
import com.chetan.domain.interactor.PublicNoteSource
import com.chetan.domain.interactor.RegisteredNoteSource
import com.chetan.domain.repository.*
import com.chetan.kotlin.notelist.NoteListAdapter
import com.chetan.kotlin.notelist.NoteListLogic
import com.chetan.kotlin.notelist.NoteListView
import com.chetan.kotlin.notelist.NoteListViewModel

class NoteListInjector(application: Application) : AndroidViewModel(application) {
    init {
        FirebaseApp.initializeApp(application)
    }

    private val anonNoteDao: AnonymousNoteDao by lazy {
        AnonymousNoteDatabase.getInstance(application).roomNoteDao()
    }

    private val regNoteDao: RegisteredNoteDao by lazy {
        RegisteredNoteDatabase.getInstance(application).roomNoteDao()
    }

    private val transactionDao: RegisteredTransactionDao by lazy {
        RoomRegisteredTransactionDatabase.getInstance(application).roomTransactionDao()
    }

    //For non-registered user persistence
    private val localAnon: ILocalNoteRepository by lazy {
        RoomLocalAnonymousRepositoryImpl(anonNoteDao)
    }

    //For registered user remote persistence (Source of Truth)
    private val remotePrivate: IRemoteNoteRepository by lazy {
        FirestorePrivateRemoteNoteImpl()
    }

    //For registered user remote persistence (Source of Truth)
    private val remotePublicRepo: IPublicNoteRepository by lazy {
        FirestoreRemoteNoteImpl
    }

    //For registered user local persistience (cache)
    private val cacheReg: ILocalNoteRepository by lazy {
        RoomLocalCacheImpl(regNoteDao)
    }

    //For registered user remote persistence (Source of Truth)
    private val remotePrivateRepo: IRemoteNoteRepository by lazy {
        RegisteredNoteRepositoryImpl(remotePrivate, cacheReg)
    }

    //For registered user local persistience (cache)
    private val transactionReg: ITransactionRepository by lazy {
        RoomTransactionRepositoryImpl(transactionDao)
    }

    //For user management
    private val auth: IAuthRepository by lazy {
        FirebaseAuthRepositoryImpl()
    }


    private lateinit var logic: NoteListLogic

    fun buildNoteListLogic(view: NoteListView): NoteListLogic {
         logic = NoteListLogic(
                DispatcherProvider,
                 NoteServiceLocator(localAnon, remotePrivateRepo, transactionReg, remotePublicRepo),
                 UserServiceLocator(auth),
                ViewModelProviders.of(view)
                        .get(NoteListViewModel::class.java),
                NoteListAdapter(),
                view,
                AnonymousNoteSource(),
                RegisteredNoteSource(),
                PublicNoteSource(),
                AuthSource()
        )

        view.setObserver(logic)

        return logic
    }
}