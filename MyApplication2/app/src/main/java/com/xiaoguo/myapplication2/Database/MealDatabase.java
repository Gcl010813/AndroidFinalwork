package com.xiaoguo.myapplication2.Database;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.xiaoguo.myapplication2.Dao.MealDao;
import com.xiaoguo.myapplication2.Entity.MealEntity;

@Database(entities = {MealEntity.class}, version = 1)
public abstract class MealDatabase extends RoomDatabase {
    public abstract MealDao mealDao();

    private static volatile MealDatabase INSTANCE;

    public static MealDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MealDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    MealDatabase.class, "meal_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}