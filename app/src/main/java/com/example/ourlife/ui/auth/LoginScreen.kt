package com.example.ourlife.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.ourlife.R
import com.example.ourlife.domain.usecases.accountValidation
import com.example.ourlife.ui.theme.Tertiary

@Composable
fun LoginContent(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    Column {
        LogoSection()
        BoxOverlay(onLoginClick, onRegisterClick)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoxOverlay(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .clip(RoundedCornerShape(30.dp)),
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(Tertiary)
    ){
        Column() {
            LoginSection(onLoginClick = onLoginClick)
            RegisterSection(onRegisterClick = onRegisterClick)
        }
    }
}

@Composable
fun LogoSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(R.drawable.ic_logo),
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp)
            )
            Text(
                text = "Our Life",
                style = MaterialTheme.typography.headlineLarge
            )
        }
    }
}

@Composable
fun LoginSection(
    onLoginClick: () -> Unit

) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errMsg by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Login",
            style = MaterialTheme.typography.headlineSmall
        )
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            leadingIcon = {
                          Icon(painter = painterResource(id = R.drawable.ic_mail_2), contentDescription = null)
            },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            leadingIcon = {
                Icon(painter = painterResource(id = R.drawable.ic_pass), contentDescription = null)
            },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp)),
            visualTransformation = PasswordVisualTransformation()
        )
        if (errMsg.isNotBlank()) {
            Text(
                text = errMsg,
                color = Color.Red,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (accountValidation(email, password) == 0) {
                    errMsg = ""
                    onLoginClick()
                } else if (accountValidation(email, password) == 1) {
                    errMsg = "All fields are required!"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Login")
        }
    }
}

@Composable
fun RegisterSection(
    onRegisterClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Create an account!",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        Button(
            onClick = { onRegisterClick() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Register")
        }
    }
}

