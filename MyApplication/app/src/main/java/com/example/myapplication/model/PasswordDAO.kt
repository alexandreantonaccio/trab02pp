package com.example.myapplication.model

import android.content.Context
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.widget.Toast

class PasswordDAO(private val context: Context) {
    private val database: SQLiteDatabase = Database(context).writableDatabase

    fun getList(): ArrayList<Password> {
        val result = ArrayList<Password>()
        val sql = "SELECT * FROM passwords ORDER BY name"
        val cursor = database.rawQuery(sql, null)

        while (cursor.moveToNext()) {
            val id = cursor.getInt(0)
            val name = cursor.getString(1)
            val login = cursor.getString(2)
            val password = cursor.getString(3)
            val notes = cursor.getString(4)
            result.add(Password(id, name, login, password, notes))
        }
        cursor.close()
        return result
    }

    fun add(password: Password): Boolean {
        val sql = "INSERT INTO passwords VALUES (NULL, " +
                "'${password.name}', " +
                "'${password.login}', " +
                "'${password.password}', " +
                "'${password.notes}')"
        return try {
            database.execSQL(sql)
            Toast.makeText(context, "Senha salva!", Toast.LENGTH_SHORT).show()
            true
        } catch (e: SQLException) {
            Toast.makeText(context, "Erro! ${e.message}", Toast.LENGTH_SHORT).show()
            false
        }
    }

    fun update(password: Password): Boolean {
        val sql = "UPDATE passwords SET " +
                "name='${password.name}', " +
                "login='${password.login}', " +
                "password='${password.password}', " +
                "notes='${password.notes}' " +
                "WHERE id=${password.id}"
        return try {
            database.execSQL(sql)
            Toast.makeText(context, "Senha atualizada!", Toast.LENGTH_SHORT).show()
            true
        } catch (e: SQLException) {
            Toast.makeText(context, "Erro! ${e.message}", Toast.LENGTH_SHORT).show()
            false
        }
    }

    fun get(id: Int): Password? {
        val sql = "SELECT * FROM passwords WHERE id=$id"
        val cursor = database.rawQuery(sql, null)
        if (cursor.moveToNext()) {
            val name = cursor.getString(1)
            val login = cursor.getString(2)
            val password = cursor.getString(3)
            val notes = cursor.getString(4)
            cursor.close()
            return Password(id, name, login, password, notes)
        }
        cursor.close()
        return null
    }
}
