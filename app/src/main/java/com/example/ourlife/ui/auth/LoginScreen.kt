package com.example.ourlife.ui.auth

import android.app.Activity
import android.content.ContentValues
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ourlife.R
import com.example.ourlife.domain.usecases.accountValidation
import com.example.ourlife.ui.theme.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

lateinit var auth: FirebaseAuth

@Composable
fun LoginContent(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    auth = Firebase.auth

    //Will only run the code only once using LaunchedEffect. (Similar to onStart in traditional Android)
    VerifyLoggedIn(onLoginClick = onLoginClick)

    Column(
        modifier = Modifier
            .background(DarkBackground)
    ) {
        LogoSection()
        LoginBoxOverlay(onLoginClick, onRegisterClick)
    }
}

@Composable
fun VerifyLoggedIn(onLoginClick: () -> Unit) {
    LaunchedEffect(Unit) {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            onLoginClick()
        }
    }
}

@Composable
fun LoginBoxOverlay(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit
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
                LoginSection(onLoginClick = onLoginClick)
                RegisterSection(onRegisterClick = onRegisterClick)
                AlternateLoginSection()
            }
        }
    }
}

@Composable
fun LogoSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
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
                    .padding(end = 16.dp)
            )
            Text(
                text = "Our Life",
                style = MaterialTheme.typography.headlineLarge,
                color = Secondary,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginSection(
    onLoginClick: () -> Unit

) {
    val currentActivity = LocalContext.current as Activity
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var errMsg by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .padding(bottom = 8.dp)
        ) {
            Text(
                text = "Login",
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                color = Primary
            )
            Text(
                text = " to your account.",
                fontSize = 20.sp,
                color = TextColor
            )
        }
        //Error Message
        Text(
            text = errMsg,
            color = Color.Red,
            modifier = Modifier.padding(top = 8.dp),
            fontSize = 12.sp,
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
                focusedContainerColor = Color.White,
                unfocusedContainerColor = BoxColor,
                disabledContainerColor = Color.Gray,
            ),
        )
        Spacer(modifier = Modifier.height(16.dp))
        //Password Text Field

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
                focusedContainerColor = Color.White,
                unfocusedContainerColor = BoxColor,
                disabledContainerColor = Color.Gray,
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
        //Login Button
        Button(
            onClick = {
                if (accountValidation(email, password) == 0) {
                    errMsg = ""
                    auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(currentActivity) { task ->
                            if (task.isSuccessful) {
                                Log.d(ContentValues.TAG, "signInWithEmail:success")
                                val user = auth.currentUser
                                onLoginClick()
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(
                                    ContentValues.TAG,
                                    "signInWithEmail:failure",
                                    task.exception
                                )
                                errMsg = "Login Failed. Please try again!"
                            }
                        }
                } else if (accountValidation(email, password) == 1) {
                    errMsg = "Email and Password are required."
                } else if (accountValidation(email, password) == 2) {
                    errMsg = "Email is required."
                } else {
                    errMsg = "Password is required."
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 64.dp),
            elevation = ButtonDefaults.buttonElevation(8.dp),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text(
                text = "Login",
                color = Color.White,
                modifier = Modifier
                    .padding(8.dp)
            )
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
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = "Create",
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = Primary
            )
            Text(
                text = " an account.",
                fontSize = 16.sp,
                color = TextColor
            )
        }
        Button(
            onClick = { onRegisterClick() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 64.dp),
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

@Composable
fun AlternateLoginSection() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = "Login",
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = Primary
            )
            Text(
                text = " using an alternate method.",
                fontSize = 16.sp,
                color = TextColor
            )
        }
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 100.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ){
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Primary),
            ){
                IconButton(onClick = {
                    Toast.makeText(context, "Not yet implemented! Work in progress.", Toast.LENGTH_SHORT).show()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_google),
                        contentDescription = null,
                        tint = BoxColor
                    )
                }
            }
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Primary),
            ){
                IconButton(onClick = {
                    Toast.makeText(context, "Not yet implemented! Work in progress.", Toast.LENGTH_SHORT).show()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_github),
                        contentDescription = null,
                        tint = BoxColor
                    )
                }
            }
        }
    }
}



