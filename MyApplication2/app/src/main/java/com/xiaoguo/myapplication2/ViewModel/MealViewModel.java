package com.xiaoguo.myapplication2.ViewModel;

import android.app.Application;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.xiaoguo.myapplication2.Dao.MealDao;
import com.xiaoguo.myapplication2.Database.MealDatabase;
import com.xiaoguo.myapplication2.Entity.MealEntity;
import java.util.List;

public class MealViewModel extends AndroidViewModel {
    private final MealDao mealDao;
    private final LiveData<List<MealEntity>> allMeals;

    public MealViewModel(@NonNull Application application) {
        super(application);
        MealDatabase database = MealDatabase.getDatabase(application);
        mealDao = database.mealDao();
        allMeals= mealDao.getAllMeals();
    }

    public LiveData<List<MealEntity>> getAllMeals() {
        return allMeals;
    }

    public void insertMeal(MealEntity meal) {
        new InsertMealAsyncTask(mealDao).execute(meal);
    }

    public LiveData<MealEntity> getRandomMeal() {
        return mealDao.getRandomMeal();
    }

    public void deleteMeal(MealEntity meal) {
        new DeleteMealAsyncTask(mealDao).execute(meal);
    }

    private static class InsertMealAsyncTask extends AsyncTask<MealEntity, Void, Void> {
        private final MealDao mealDao;

        InsertMealAsyncTask(MealDao dao) {
            mealDao = dao;
        }

        @Override
        protected Void doInBackground(MealEntity... meals) {
            mealDao.insertMeal(meals[0]);
            return null;
        }
    }

    private static class DeleteMealAsyncTask extends AsyncTask<MealEntity, Void, Void> {
        private final MealDao mealDao;

        DeleteMealAsyncTask(MealDao dao) {
            mealDao = dao;
        }

        @Override
        protected Void doInBackground(MealEntity... meals) {
            mealDao.deleteMeal(meals[0]);
            return null;
        }
    }
}