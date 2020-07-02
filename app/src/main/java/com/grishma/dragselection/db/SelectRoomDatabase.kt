package com.grishma.dragselection.db

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.grishma.dragselection.model.DataModel

/**
 * Created by  grishma on 18/6/20.
 */
@Database(entities = [DataModel::class], version = 1)
abstract class SelectRoomDatabase : RoomDatabase() {

    /**
     * This is the Selection data access object instance
     * @return the dao to phrase database operations
     */
    abstract fun selectDao(): SelectDao

    companion object {

        /**
         * This is just for singleton pattern
         */
        private var INSTANCE: SelectRoomDatabase? = null

        fun getDatabase(context: Context): SelectRoomDatabase {
            if (INSTANCE == null) {
                synchronized(SelectRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        // Get RoomDatabase database instance
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                                SelectRoomDatabase::class.java, "select_database")
                                .allowMainThreadQueries()
                                .build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}
