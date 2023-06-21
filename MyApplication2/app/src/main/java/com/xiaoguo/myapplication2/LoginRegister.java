package com.xiaoguo.myapplication2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.xiaoguo.myapplication2.Dao.UserDao;
import com.xiaoguo.myapplication2.Database.UserDatabase;
import com.xiaoguo.myapplication2.Entity.UserEntity;
import com.xiaoguo.myapplication2.ViewModel.UserViewModel;

public class LoginRegister extends AppCompatActivity {
    private EditText editTextUsername;
    private EditText editTextPassword;
    private UserDao userDao;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        userDao = UserDatabase.getDatabase(this).userDao();

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        Button buttonLogin = findViewById(R.id.buttonLogin);
        Button buttonRegister = findViewById(R.id.buttonRegister);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                LiveData<UserEntity> userLiveData = userDao.getUserByUsername(username);
                userLiveData.observe(LoginRegister.this, new Observer<UserEntity>() {
                    @Override
                    public void onChanged(UserEntity user) {
                        userLiveData.removeObserver(this);
                        if (user != null) {
                            if (user.getPassword().equals(password)) {
                                Toast.makeText(LoginRegister.this, "登录成功", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginRegister.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(LoginRegister.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                                editTextUsername.setText("");
                                editTextPassword.setText("");
                            }
                        } else {
                            Toast.makeText(LoginRegister.this, "用户不存在,先注册", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (!username.isEmpty() && !password.isEmpty()) {
                    UserEntity newUser = new UserEntity(username, password);
                    userViewModel.insertUser(newUser);
                    Toast.makeText(LoginRegister.this, "注册成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginRegister.this, "注册失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}