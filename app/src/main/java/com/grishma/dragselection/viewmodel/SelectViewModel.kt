package com.grishma.dragselection.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.grishma.dragselection.db.SelectRoomDatabase
import com.grishma.dragselection.model.DataModel

/**
 * Created by grishma on 23/06/20.
 */
class SelectViewModel(app: Application) : AndroidViewModel(app) {

    private val database = SelectRoomDatabase.getDatabase(app) //get database
    private val selectDao = database.selectDao() //get dao class

    fun insert(dataModel: MutableList<DataModel?>) =
        selectDao.insert(dataModel) //insert to database

    fun getAllSelectedItems() = selectDao.allSelectedData() //get all selected items as a list

    companion object {
        private const val TAG = "SelectViewModel"
    }
}