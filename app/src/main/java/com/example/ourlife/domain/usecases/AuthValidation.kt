package com.example.ourlife.domain.usecases

fun accountValidation(email: String, pass: String): Int {
    return if(email.isNotEmpty() and pass.isNotEmpty()) 0
    else if (email.isEmpty() and pass.isEmpty()) 1
    else if(email.isEmpty()) 2
    else if(pass.isEmpty()) 3
    else 4
}

