package com.example.myapplication.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Database(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_PASS)
        db.execSQL(SQL_POPULATE_PASS)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_PASS)
        onCreate(db)
    }

    companion object {
        const val DATABASE_VERSION = 4
        const val DATABASE_NAME = "PlainText.db"

        private const val SQL_CREATE_PASS = "CREATE TABLE passwords (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, login TEXT, " +
                "password TEXT, notes TEXT)"

        private const val SQL_POPULATE_PASS = "INSERT INTO passwords VALUES " +
                "(NULL, 'GMail', 'dovahkiin', 'Teste123', 'Nota de Teste')"

        private const val SQL_DELETE_PASS = "DROP TABLE IF EXISTS passwords"
    }
}
