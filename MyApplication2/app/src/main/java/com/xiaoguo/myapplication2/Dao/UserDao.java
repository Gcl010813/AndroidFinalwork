package com.xiaoguo.myapplication2.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.xiaoguo.myapplication2.Entity.UserEntity;

@Dao
public interface UserDao {
    @Insert
    void insertUser(UserEntity user);

    @Query("SELECT * FROM user_table WHERE username = :username")
    LiveData<UserEntity> getUserByUsername(String username);
}