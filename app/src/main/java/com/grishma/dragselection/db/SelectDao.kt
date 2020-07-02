package com.grishma.dragselection.db


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.grishma.dragselection.model.DataModel

/**
 * Created by grishma on 18/6/20.
 * The Data Access Object for Phrase entity operations in database.
 */
@Dao
interface SelectDao {

    /**
     * Get all selected data from database ordered by ASC
     * @return a list with all selected data
     */
    @Query("SELECT * FROM selection ORDER BY id ASC")
    fun allSelectedData(): LiveData<MutableList<DataModel>>


    /**
     * Function to insert list in room database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dataModel : MutableList<DataModel?>)

}
