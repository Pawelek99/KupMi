package com.pointlessapps.kupmi.ui.lists

import android.content.Context
import com.pointlessapps.kupmi.models.AppDatabase

class RepositoryLists(context: Context) {
    val itemsDao = AppDatabase.init(context).itemDao()
}
