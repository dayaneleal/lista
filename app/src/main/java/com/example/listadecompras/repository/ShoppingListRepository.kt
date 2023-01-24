package com.example.listadecompras.repository

import android.content.ContentValues
import android.content.Context
import com.example.listadecompras.constants.DBConstants
import com.example.listadecompras.model.ShoppingListModel

class ShoppingListRepository private constructor(context: Context) {
    private val listDatabase = ListDatabase(context)

    companion object {
        private lateinit var repository: ShoppingListRepository

        fun getInstance(context: Context): ShoppingListRepository {
            if (!::repository.isInitialized) {
                repository = ShoppingListRepository(context)
            }
            return repository
        }
    }

    fun insert(shoppingList: ShoppingListModel): Boolean {
        return try {
            val db = listDatabase.writableDatabase

            val values = ContentValues()
            values.put(DBConstants.SHOPPING_LIST.COLUMNS.ITEM_NAME, shoppingList.itemName)
            values.put(DBConstants.SHOPPING_LIST.COLUMNS.QUANTITY, shoppingList.itemQuantity)

            db.insert(DBConstants.SHOPPING_LIST.TABLE_NAME, null, values)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getAll(): List<ShoppingListModel> {

        val list = mutableListOf<ShoppingListModel>()

        try {
            val db = listDatabase.readableDatabase

            val selection = arrayOf(
                DBConstants.SHOPPING_LIST.COLUMNS.ID,
                DBConstants.SHOPPING_LIST.COLUMNS.ITEM_NAME,
                DBConstants.SHOPPING_LIST.COLUMNS.QUANTITY
            )

            val cursor = db.query(
                DBConstants.SHOPPING_LIST.TABLE_NAME, selection,
                null, null,
                null, null, null
            )

            //0,    1,      2
            //id, name, presence

            //Verifica se tem alguma coisa
            if (cursor != null && cursor.count > 0){
                while(cursor.moveToNext()) {

                    val id =
                        cursor.getInt(cursor.getColumnIndex(DBConstants.SHOPPING_LIST.COLUMNS.ID))
                    val name =
                        cursor.getString(cursor.getColumnIndex(DBConstants.SHOPPING_LIST.COLUMNS.ITEM_NAME))
                    val quantity =
                        cursor.getInt(cursor.getColumnIndex(DBConstants.SHOPPING_LIST.COLUMNS.QUANTITY))

                    val item = ShoppingListModel(id, name, quantity)
                    list.add(ShoppingListModel(id,name,quantity))
                }
            }

            cursor.close()
        } catch (e: Exception) {
            return list
        }
        return list
    }

    fun get(id: Int): ShoppingListModel? {

        var list : ShoppingListModel? = null

        try {
            val db = listDatabase.readableDatabase

            val selection = DBConstants.SHOPPING_LIST.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())

            val projection = arrayOf(
                DBConstants.SHOPPING_LIST.COLUMNS.ID,
                DBConstants.SHOPPING_LIST.COLUMNS.ITEM_NAME,
                DBConstants.SHOPPING_LIST.COLUMNS.QUANTITY
            )

            val cursor = db.query(
                DBConstants.SHOPPING_LIST.TABLE_NAME, projection,
                selection, args,
                null, null, null
            )

            //0,    1,      2
            //id, name, presence

            //Verifica se tem alguma coisa
            if (cursor != null && cursor.count > 0){
                while(cursor.moveToNext()) {

                    val name =
                        cursor.getString(cursor.getColumnIndex(DBConstants.SHOPPING_LIST.COLUMNS.ITEM_NAME))
                    val quantity =
                        cursor.getInt(cursor.getColumnIndex(DBConstants.SHOPPING_LIST.COLUMNS.QUANTITY))

                    list = ShoppingListModel(id, name, quantity)
                }
            }

            cursor.close()
        } catch (e: Exception) {
            return list
        }
        return list
    }
}