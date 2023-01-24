package com.example.listadecompras.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.listadecompras.constants.DBConstants

class ListDatabase(context: Context) : SQLiteOpenHelper(context, NAME, null, VERSION) {

    companion object{
        private const val NAME = "shoppingListDB"
        private const val VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        //Chamado na criação do banco
        db.execSQL("CREATE TABLE " + DBConstants.SHOPPING_LIST.TABLE_NAME + " (" +
                DBConstants.SHOPPING_LIST.COLUMNS.ID + " integer primary key autoincrement, " +
                DBConstants.SHOPPING_LIST.COLUMNS.ITEM_NAME + " text, " +
                DBConstants.SHOPPING_LIST.COLUMNS.QUANTITY + " integer);")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //Cai aqui quando a versão do banco do usuário for diferente da atual
    }

}