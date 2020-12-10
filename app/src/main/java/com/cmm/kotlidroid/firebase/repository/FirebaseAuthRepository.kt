package com.cmm.kotlidroid.firebase.repository

import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.cmm.kotlidroid.R
import com.cmm.kotlidroid.architecture.CustomApplication
import com.cmm.kotlidroid.firebase.exception.AuthInfos
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser


class FirebaseAuthRepository {

    val bleuh: Resources = CustomApplication.instance.resources
    private var mFirebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    var mCurrentUser = MutableLiveData<FirebaseUser>()
    var mErrorProcessBis = MutableLiveData<AuthInfos>()

    init {
        if (mFirebaseAuth.currentUser != null) {
            mCurrentUser.postValue(mFirebaseAuth.currentUser)
        } else {
            mErrorProcessBis.postValue(
                AuthInfos(
                    "Null user",
                    bleuh.getString(R.string.null_user)
                )
            )
        }
    }


    fun registerNewUser(email: String, password: String) {
        mFirebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    mCurrentUser.postValue(mFirebaseAuth.currentUser)
                } else {
                    val errorCode = (task.exception as FirebaseAuthException?)!!.errorCode
                    Log.d("REGISTER", "registerNewUser: $errorCode ")
                    when (errorCode) {
                        "ERROR_EMAIL_ALREADY_IN_USE" -> mErrorProcessBis.postValue(
                            AuthInfos(
                                "Email already in use",
                                bleuh.getString(R.string.email_already_in_use)
                            )
                        )
                        "ERROR_INVALID_CREDENTIAL" -> mErrorProcessBis.postValue(
                            AuthInfos(
                                "Invalid email or password",
                                bleuh.getString(R.string.invalid_email_or_password)
                            )
                        )
                        "ERROR_INVALID_EMAIL" -> mErrorProcessBis.postValue(
                            AuthInfos(
                                "Invalid email",
                                bleuh.getString(R.string.invalid_email)
                            )
                        )
                        "ERROR_WEAK_PASSWORD" -> mErrorProcessBis.postValue(
                            AuthInfos(
                                "Weak password",
                                bleuh.getString(R.string.weak_password)
                            )
                        )
                    }
                }
            }
    }

    fun loginUser(email: String, password: String) {
        mFirebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    mCurrentUser.postValue(mFirebaseAuth.currentUser)
                } else {
                    val errorCode = (task.exception as FirebaseAuthException?)!!.errorCode
                    when (errorCode) {
                        "ERROR_USER_DISABLED" -> mErrorProcessBis.postValue(
                            AuthInfos(
                                "User disabled",
                                bleuh.getString(R.string.user_disabled)
                            )
                        )
                        "ERROR_INVALID_EMAIL" -> mErrorProcessBis.postValue(
                            AuthInfos(
                                "Invalid email",
                                bleuh.getString(R.string.invalid_email)
                            )
                        )
                        "ERROR_USER_NOT_FOUND" -> mErrorProcessBis.postValue(
                            AuthInfos(
                                "User not found",
                                bleuh.getString(R.string.user_not_found)
                            )
                        )
                        "ERROR_USER_TOKEN_EXPIRED" -> mErrorProcessBis.postValue(
                            AuthInfos(
                                "Token revoked",
                                "User's token has been revoked"
                            )
                        )
                        "ERROR_INVALID_USER_TOKEN" -> mErrorProcessBis.postValue(
                            AuthInfos(
                                "Token malformed",
                                "User's token is malformed"
                            )
                        )
                    }
                }
            }
    }

    fun disconnectUser() {
        if (mFirebaseAuth.currentUser != null) {
            mFirebaseAuth.signOut()
            mErrorProcessBis.postValue(
                AuthInfos(
                    "Signing out", bleuh.getString(R.string.disconnected)
                )
            )
        }
    }
}