package com.chetan.kotlin.login

import com.google.android.gms.auth.api.signin.GoogleSignInAccount

/**
 * Wrapper class for data recieved in LoginActivity's onActivityResult()
 * function
 */
data class LoginResult(val requestCode: Int, val account: GoogleSignInAccount?)