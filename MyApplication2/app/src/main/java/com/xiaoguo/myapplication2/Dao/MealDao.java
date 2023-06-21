package com.xiaoguo.myapplication2.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.xiaoguo.myapplication2.Entity.MealEntity;
import java.util.List;

@Dao
public interface MealDao {
    @Insert
    void insertMeal(MealEntity meal);

    @Query("SELECT * FROM meal_table")
    LiveData<List<MealEntity>> getAllMeals();

    @Query("SELECT * FROM meal_table ORDER BY RANDOM() LIMIT 1")
    LiveData<MealEntity> getRandomMeal();

    @Delete
    void deleteMeal(MealEntity meal);
}