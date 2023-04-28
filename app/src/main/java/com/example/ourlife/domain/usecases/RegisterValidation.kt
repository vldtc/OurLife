package com.example.ourlife.domain.usecases

fun registerValidation(email: String, pass: String, passConfirm: String): Int {
    return if(email.isNotEmpty() and pass.isNotEmpty() and passConfirm.isNotEmpty()) 0
    else if (email.isEmpty() or pass.isEmpty() or passConfirm.isEmpty()) 1
    else 2
}