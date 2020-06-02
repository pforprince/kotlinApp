package com.chetan.kotlin.login.buildlogic

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.FirebaseApp
import com.chetan.data.auth.FirebaseAuthRepositoryImpl
import com.chetan.domain.DispatcherProvider
import com.chetan.domain.servicelocator.UserServiceLocator
import com.chetan.domain.interactor.AuthSource
import com.chetan.domain.repository.IAuthRepository
import com.chetan.kotlin.login.LoginActivity
import com.chetan.kotlin.login.LoginLogic

class LoginInjector(application: Application) : AndroidViewModel(application) {
    init {
        FirebaseApp.initializeApp(application)
    }

    //For user management
    private val auth: IAuthRepository by lazy {
        //by using lazy, I don't load this resource until I need it
        FirebaseAuthRepositoryImpl()
    }


    fun buildLoginLogic(view: LoginActivity): LoginLogic = LoginLogic(
            DispatcherProvider,
            UserServiceLocator(auth),
            view,
            AuthSource()
    ).also { view.setObserver(it) }
}