package com.cmm.kotlidroid.firebase

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cmm.kotlidroid.R
import com.cmm.kotlidroid.firebase.exception.AuthInfos
import com.cmm.kotlidroid.firebase.viewmodel.FirebaseAuthViewModel
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_firebase.*


class FirebaseActivity : AppCompatActivity() {

    private lateinit var firebaseViewModel: FirebaseAuthViewModel
    private var observerUser = Observer<FirebaseUser> {
        updateUser(it)
    }
    private var observerErrorBis = Observer<AuthInfos> {
        toastInfo(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase)

        firebaseViewModel = ViewModelProvider(this)[FirebaseAuthViewModel::class.java]
    }

    override fun onStart() {
        super.onStart()
        firebaseViewModel.mCurrentUser.observe(this, observerUser)
        firebaseViewModel.mErrorProcessBis.observe(this, observerErrorBis)
    }

    override fun onStop() {
        firebaseViewModel.mCurrentUser.removeObserver(observerUser)
        firebaseViewModel.mErrorProcessBis.removeObserver(observerErrorBis)
        super.onStop()
    }

    private fun checkConformityFields(): Boolean {
        var isValid = true

        if (firebaseUserEmail.text.toString().isBlank() ||
            firebaseUserPassword.text.toString().isBlank()
        ) {
            isValid = false
            Toast.makeText(this, this.getString(R.string.email_password_blank), Toast.LENGTH_SHORT)
                .show()
        }
        return isValid
    }

    fun login(view: View) {
        if (checkConformityFields()) {
            firebaseViewModel.loginUser(
                firebaseUserEmail.text.toString(),
                firebaseUserPassword.text.toString()
            )
        } else {
            Toast.makeText(this, this.getString(R.string.email_password_blank), Toast.LENGTH_LONG)
                .show()
        }

    }

    fun register(view: View) {
        if (checkConformityFields()) {
            firebaseViewModel.registerNewUser(
                firebaseUserEmail.text.toString(),
                firebaseUserPassword.text.toString()
            )
        }
    }

    fun disconnect(view: View) {
        firebaseViewModel.disconnectUser()
    }

    private fun updateUser(user: FirebaseUser) {
        user.let {
            Toast.makeText(this, "${user.uid}-${user.email}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun toastInfo(e: AuthInfos) {
        Toast.makeText(this, "Info : ${e.message}", Toast.LENGTH_SHORT).show()
    }

}