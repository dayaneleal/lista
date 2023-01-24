package com.example.listadecompras.constants;

class DBConstants private constructor(){

    object SHOPPING_LIST{
        const val TABLE_NAME = "List"
        const val ID = "listId"

        object COLUMNS {
            const val ID = "id"
            const val ITEM_NAME = "item_name"
            const val QUANTITY = "quantity"
        }
    }
}
