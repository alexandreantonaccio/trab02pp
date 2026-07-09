package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.myapplication.ui.LoginScreen
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("PlainText", "Activity principal criada")
        setContent {
            MyApplicationTheme {
                LoginScreen()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("PlainText", "Método onStart executado")
    }

    override fun onResume() {
        super.onResume()
        Log.i("PlainText", "Método onResume executado")
    }

    override fun onPause() {
        super.onPause()
        Log.i("PlainText", "Método onPause executado")
    }

    override fun onStop() {
        super.onStop()
        Log.i("PlainText", "Método onStop executado")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("PlainText", "Método onDestroy executado")
    }
}
