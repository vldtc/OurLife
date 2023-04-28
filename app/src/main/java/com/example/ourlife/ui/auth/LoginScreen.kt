package com.example.ourlife.ui.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@Composable
fun LoginContent(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit
){
    Column {
        LoginSection(onLoginClick = onLoginClick)
        RegisterSection(onRegisterClick = onRegisterClick)
    }
}

@Composable
fun LoginSection(
    onLoginClick: () -> Unit
){
    Button(
        onClick = { onLoginClick() }
    ) {
        Text(text = "Login")
    }
}

@Composable
fun RegisterSection(
    onRegisterClick: () -> Unit
){
    Button(
        onClick = { onRegisterClick() }
    ) {
        Text(text = "Register")
    }
}

