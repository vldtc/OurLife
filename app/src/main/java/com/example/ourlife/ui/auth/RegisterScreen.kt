package com.example.ourlife.ui.auth

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ourlife.R
import com.example.ourlife.domain.usecases.registerValidation
import com.example.ourlife.ui.theme.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegisterContent(
    onSuccessRegister: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(Color.Black)
    ) {
        Box{
            BackSection(onSuccessRegister = onSuccessRegister)
            LogoSection()
        }
        RegisterBoxOverlay(onSuccessRegister = onSuccessRegister)
    }
}

@Composable
fun BackSection(
    onSuccessRegister: () -> Unit
){
    IconButton(
        onClick = { onSuccessRegister() },
    ){
        Icon(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = null,
            tint = BoxColor
        )
    }
}

@Composable
fun RegisterBoxOverlay(
    onSuccessRegister: () -> Unit
) {
    Surface(
        shadowElevation = 8.dp,
        shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .background(BoxColor)
        ) {
            Column {
                RegisterContentSection(onSuccessRegister = onSuccessRegister)
            }
        }
    }
}

@Composable
fun RegisterContentSection(
    onSuccessRegister: () -> Unit
) {
    val currentActivity = LocalContext.current as Activity
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var passwordConfirm by remember { mutableStateOf("") }
    var errMsg by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .padding(top = 24.dp)
        ) {
            Text(
                text = "Register",
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                color = Primary
            )
            Text(
                text = " a new account.",
                fontSize = 20.sp,
                color = TextColor
            )
        }
        //Error Message Text
        Text(
            text = errMsg,
            color = Color.Red,
            modifier = Modifier.padding(top = 16.dp),
            fontSize = 10.sp,
            fontWeight = FontWeight.ExtraBold
        )
        Spacer(modifier = Modifier.height(16.dp))
        //Email Text Field
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = {
                Text(
                    text = "Email",
                    color = Primary
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_mail_2),
                    contentDescription = null
                )
            },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Primary,
                focusedContainerColor = Primary,
                unfocusedContainerColor = Secondary,
                disabledContainerColor = Tertiary,
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = {
                Text(
                    text = "Password",
                    color = Primary
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_pass),
                    contentDescription = null
                )
            },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Primary,
                focusedContainerColor = Primary,
                unfocusedContainerColor = Secondary,
                disabledContainerColor = Tertiary,
            ),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        painter = painterResource(if (passwordVisible) R.drawable.ic_visibility_off else R.drawable.ic_visibility),
                        contentDescription = if (passwordVisible) "Hide password" else "Show password"
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        //Password Confirmation Text Field
        OutlinedTextField(
            value = passwordConfirm,
            onValueChange = { passwordConfirm = it },
            label = {
                Text(
                    text = "Password Confirmation",
                    color = Primary
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_pass),
                    contentDescription = null
                )
            },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Primary,
                focusedContainerColor = Primary,
                unfocusedContainerColor = Secondary,
                disabledContainerColor = Tertiary,
            ),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (registerValidation(email, password, passwordConfirm) == 1) {
                    errMsg = "All the fields are required!"
                } else if (registerValidation(email, password, passwordConfirm) == 2) {
                    errMsg = "Enter a valid email address!"
                } else if (registerValidation(email, password, passwordConfirm) == 3) {
                    errMsg = "Passwords don't match!"
                } else if (registerValidation(email, password, passwordConfirm) == 4) {
                    errMsg =
                        "Pasword not valid. Must have at least 6 character. 1 uppercase. 1 special character."
                } else {
                    errMsg = ""
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(currentActivity) { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success")
                                onSuccessRegister()
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.exception)
                                errMsg = "Failed to register. Email address already in use!"
                            }
                        }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            elevation = ButtonDefaults.buttonElevation(8.dp),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text(
                text = "Register",
                color = Color.White,
                modifier = Modifier
                    .padding(8.dp)
            )
        }
    }
}