package com.example.comp_admin.sqlitedbtest.helpers

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.comp_admin.sqlitedbtest.models.Employee

class DBHelper(var mContext : Context):SQLiteOpenHelper(mContext, DATABASE_NAME, null,
    DATABASE_VERSION)
{
    var db : SQLiteDatabase = writableDatabase

    companion object{
        private const val DATABASE_NAME = "mydb"
        private const val DATABASE_VERSION = 2
        private const val TABLE_NAME = "employee"
        private const val COLUMNS_ID = "id"
        private const val COLUMNS_NAME = "name"
        private const val COLUMNS_EMAIL = "email"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        var createTable = "create table $TABLE_NAME($COLUMNS_ID INTEGER, $COLUMNS_NAME text, $COLUMNS_EMAIL text, password TEXT)"
        db?.execSQL(createTable)
        Log.d("db test", "onCreate")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        val dropTable = "drop table $TABLE_NAME"
        db?.execSQL(dropTable)
        onCreate(db)
        Log.d("db test", "onUpgrade")
    }
    fun addEmpployee(employee: Employee){

        var contentValues = ContentValues()
        contentValues.put(COLUMNS_ID, employee.id)
        contentValues.put(COLUMNS_NAME, employee.name)
        contentValues.put(COLUMNS_EMAIL, employee.email)

        db.insert(TABLE_NAME, null, contentValues)
        Log.d("db test", "Add employee")

    }
}
