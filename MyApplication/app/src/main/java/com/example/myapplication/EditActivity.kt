package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.model.Password
import com.example.myapplication.model.PasswordDAO

class EditActivity : AppCompatActivity() {
    private lateinit var passwordDAO: PasswordDAO
    private var passwordId: Int = -1
    private lateinit var editName: EditText
    private lateinit var editLogin: EditText
    private lateinit var editPassword: EditText
    private lateinit var editNotes: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        editName = findViewById(R.id.addName)
        editLogin = findViewById(R.id.addLogin)
        editPassword = findViewById(R.id.addPassword)
        editNotes = findViewById(R.id.addNotes)
        
        passwordDAO = PasswordDAO(this)
        
        passwordId = intent.getIntExtra("passwordId", -1)
        
        if (passwordId != -1) {
            val password = passwordDAO.get(passwordId)
            password?.let {
                editName.setText(it.name)
                editLogin.setText(it.login)
                editPassword.setText(it.password)
                editNotes.setText(it.notes)
            }
        }
        
        findViewById<Button>(R.id.buttonSave).setOnClickListener {
            salvarClicado(it)
        }
    }

    fun salvarClicado(view: View) {
        val password = Password(
            passwordId,
            editName.text.toString(),
            editLogin.text.toString(),
            editPassword.text.toString(),
            editNotes.text.toString()
        )
        
        val result = if (passwordId == -1) {
            passwordDAO.add(password)
        } else {
            passwordDAO.update(password)
        }
        
        if (result) finish()
    }
}
