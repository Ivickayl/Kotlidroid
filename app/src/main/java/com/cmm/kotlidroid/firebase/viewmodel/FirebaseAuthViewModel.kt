package com.cmm.kotlidroid.firebase.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cmm.kotlidroid.firebase.exception.AuthInfos
import com.cmm.kotlidroid.firebase.repository.FirebaseAuthRepository
import com.google.firebase.auth.FirebaseUser

class FirebaseAuthViewModel : ViewModel() {

    private val mFirebaseAuthRepository: FirebaseAuthRepository by lazy { FirebaseAuthRepository() }
    var mCurrentUser = MutableLiveData<FirebaseUser>()
    var mErrorProcessBis = MutableLiveData<AuthInfos>()

    init {
        mCurrentUser = mFirebaseAuthRepository.mCurrentUser
        mErrorProcessBis = mFirebaseAuthRepository.mErrorProcessBis
    }

    fun loginUser(email: String, password: String) {
        mFirebaseAuthRepository.loginUser(email, password)
    }

    fun registerNewUser(email: String, password: String) {
        mFirebaseAuthRepository.registerNewUser(email, password)
    }

    fun disconnectUser() {
        mFirebaseAuthRepository.disconnectUser()
    }
}