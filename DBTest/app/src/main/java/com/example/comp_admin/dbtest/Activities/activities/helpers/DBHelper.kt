package com.example.comp_admin.dbtest.Activities.activities.helpers

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.comp_admin.dbtest.Activities.activities.models.Employee

class DBHelper(var mContext: Context) :
    SQLiteOpenHelper(mContext, DATABASE_NAME, null, DATABASE_VERSION_CODE) {

    var database: SQLiteDatabase = writableDatabase

    companion object {
        private const val DATABASE_NAME = "mydb"
        private const val TABLE_NAME = "emp"
        private const val DATABASE_VERSION_CODE = 1
        private const val COLUMNS_ID = "id"
        private const val COLUMNS_NAME = "name"
        private const val COLUMNS_EMAIL = "email"

    }
  

    override fun onCreate(p0: SQLiteDatabase) {
        var createTable = "create table $TABLE_NAME(" +
                "$COLUMNS_ID INTEGER, " +
                "$COLUMNS_NAME text, " +
                "$COLUMNS_EMAIL text, " +
                "password text)"
        p0.execSQL(createTable)
        Log.d("db test", "onCreate")
    }

    override fun onUpgrade(p0: SQLiteDatabase, p1: Int, p2: Int) {
        var dropTable = "drop table $TABLE_NAME"
        p0.execSQL(dropTable)
        onCreate(p0)
        Log.d("db test", "onUpgrade")
    }

    fun createEmployee(employee: Employee) {
        var contentValues = ContentValues()
        contentValues.put(COLUMNS_ID, employee.id)
        contentValues.put(COLUMNS_NAME, employee.name)
        contentValues.put(COLUMNS_EMAIL, employee.email)

        database.insert(TABLE_NAME,null,contentValues)
        Log.d("db test","add employee")
    }

    fun readEmployee(): ArrayList<Employee> {
        var mList: ArrayList<Employee> = ArrayList()
        var columns = arrayOf(
            COLUMNS_ID,
            COLUMNS_NAME,
            COLUMNS_EMAIL
        )
        var cursor = database.query(TABLE_NAME, columns, null, null, null, null, null)
        if(cursor !=null && cursor.moveToFirst()){
            do{
                var id = cursor.getInt(cursor.getColumnIndex(COLUMNS_ID))
                var name = cursor.getString(cursor.getColumnIndex(COLUMNS_NAME))
                var email = cursor.getString(cursor.getColumnIndex(COLUMNS_EMAIL))
                var employee = Employee(id, name, email)
                mList.add(employee)
            }while (cursor.moveToNext())
        }
        cursor.close()
        return mList
    }

    fun updateEmployee(updateEmployee: Employee) {
        var whereClause = "$COLUMNS_ID=?"
        var whereArgs = arrayOf(updateEmployee.id.toString())
        var contentValues = ContentValues()
        contentValues.put(COLUMNS_NAME, updateEmployee.name)
        contentValues.put(COLUMNS_EMAIL, updateEmployee.email)
        database.update(TABLE_NAME, contentValues, whereClause, whereArgs)
    }

    fun deleteEmployee(deleteEmployee: Employee) {
        var whereClause = "$COLUMNS_ID=?"
        var whereArgs = arrayOf(deleteEmployee.id.toString())
        database.delete(TABLE_NAME,whereClause,whereArgs)
    }

}