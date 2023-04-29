package com.example.ourlife.domain.usecases

import android.util.Patterns

fun registerValidation(email: String, pass: String, passConfirm: String): Int {
    return if (email.isEmpty() || pass.isEmpty() || passConfirm.isEmpty()) {
        // if any field is empty
        1
    } else if (!isEmailValid(email)) {
        // if email is not valid
        2
    } else if (!isPasswordMatch(pass, passConfirm)) {
        // if password does not match passConfirm
        3
    } else if (!isPasswordValid(pass)){
        4
    }else {
        // all fields are valid
        5
    }
}

fun isEmailValid(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun isPasswordMatch(pass: String, passConfirm: String): Boolean {
    return pass == passConfirm
}

fun isPasswordValid(password: String): Boolean {
    val passwordPattern = "^(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{6,}$".toRegex()
    return passwordPattern.matches(password)
}

