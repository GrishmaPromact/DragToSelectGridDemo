package com.grishma.dragselection.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by grishma on 23/06/2020.
 */
@Entity(tableName = "selection")
public class DataModel {

    @PrimaryKey(autoGenerate = true)
    public int id=0;

    private boolean isSelected;
    private int position;

    public DataModel(boolean isSelected, int position) {
        this.isSelected = isSelected;
        this.position = position;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}
