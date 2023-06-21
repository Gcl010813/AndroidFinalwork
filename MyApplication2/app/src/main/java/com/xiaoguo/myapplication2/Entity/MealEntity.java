package com.xiaoguo.myapplication2.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "meal_table")
public class MealEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String description;

    public MealEntity(String name,String description) {
        this.name = name;
        this.description=description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}