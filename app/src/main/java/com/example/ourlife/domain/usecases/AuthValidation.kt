package com.example.ourlife.domain.usecases

import android.app.Activity
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.ourlife.ui.auth.auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.GoogleAuthProvider

fun accountValidation(email: String, pass: String): Int {
    return if(email.isNotEmpty() and pass.isNotEmpty()) 0
    else if (email.isEmpty() and pass.isEmpty()) 1
    else if(email.isEmpty()) 2
    else if(pass.isEmpty()) 3
    else 4
}




