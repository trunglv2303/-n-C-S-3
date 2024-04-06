package com.example.appbanhang.sceens

import android.widget.Toast.makeText
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun Toast(message:String)
{
    makeText(LocalContext.current,message,android.widget.Toast.LENGTH_SHORT).show()
}