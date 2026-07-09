package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.model.Password
import com.example.myapplication.model.PasswordDAO
import com.example.myapplication.ui.theme.MyApplicationTheme

class ListActivity : ComponentActivity() {
    private lateinit var passwordDAO: PasswordDAO
    private val passwords = mutableStateListOf<Password>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("PlainText", "ListActivity criada")
        
        passwordDAO = PasswordDAO(this)
        
        val login = intent.getStringExtra("login") ?: "User"
        
        setContent {
            MyApplicationTheme {
                ListScreen(login, passwords) { password ->
                    val intent = Intent(this, EditActivity::class.java).apply {
                        putExtra("passwordId", password.id)
                    }
                    startActivity(intent)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Log.i("PlainText", "ListActivity onResume")
        passwords.clear()
        passwords.addAll(passwordDAO.getList())
    }

    override fun onStart() {
        super.onStart()
        Log.i("PlainText", "ListActivity onStart")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    login: String, 
    passwords: List<Password>,
    onItemClick: (Password) -> Unit
) {
    val context = LocalContext.current
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("PlainText", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF37474F))
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    val intent = Intent(context, EditActivity::class.java)
                    context.startActivity(intent)
                },
                containerColor = Color(0xFF37474F),
                contentColor = Color(0xFF8BC34A)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_input_add),
                    contentDescription = "Add Password",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text(
                text = "Olá $login!",
                fontSize = 18.sp,
                modifier = Modifier.padding(16.dp)
            )
            
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(passwords) { password ->
                    PasswordItem(password = password, onClick = { onItemClick(password) })
                    HorizontalDivider(color = Color.LightGray, thickness = 0.5.dp)
                }
            }
        }
    }
}

@Composable
fun PasswordItem(password: Password, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_item_key),
            contentDescription = null,
            modifier = Modifier.size(50.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = password.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF39393A)
            )
            Text(
                text = password.login,
                fontSize = 14.sp,
                color = Color(0xFF39393A)
            )
        }
        Image(
            painter = painterResource(id = R.drawable.ic_right_arrow),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }
}
