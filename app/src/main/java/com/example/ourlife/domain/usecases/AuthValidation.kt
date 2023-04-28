package com.example.ourlife.domain.usecases

fun accountValidation(email: String, pass: String): Int {
    return if(email.isNotEmpty() and pass.isNotEmpty()) 0
    else if (email.isEmpty() or pass.isEmpty()) 1
    else 2
}