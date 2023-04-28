package com.example.ourlife.ui.main

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.ourlife.navgraphs.BottomBarScreen

@Composable
fun ProfileContent(){
    Text(text = BottomBarScreen.Profile.title)
}