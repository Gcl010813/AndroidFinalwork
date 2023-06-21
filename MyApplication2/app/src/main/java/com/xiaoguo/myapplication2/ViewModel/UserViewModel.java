package com.xiaoguo.myapplication2.ViewModel;

import android.app.Application;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import com.xiaoguo.myapplication2.Dao.UserDao;
import com.xiaoguo.myapplication2.Database.UserDatabase;
import com.xiaoguo.myapplication2.Entity.UserEntity;

public class UserViewModel extends AndroidViewModel {
    private UserDao userDao;

    public UserViewModel(@NonNull Application application) {
        super(application);
        UserDatabase database = UserDatabase.getDatabase(application);
        userDao = database.userDao();
    }

    public void insertUser(UserEntity user) {
        new InsertUserAsyncTask(userDao).execute(user);
    }

    private static class InsertUserAsyncTask extends AsyncTask<UserEntity, Void, Void> {
        private final UserDao userDao;

        InsertUserAsyncTask(UserDao dao) {
            userDao = dao;
        }

        @Override
        protected Void doInBackground(UserEntity... users) {
            userDao.insertUser(users[0]);
            return null;
        }
    }
}