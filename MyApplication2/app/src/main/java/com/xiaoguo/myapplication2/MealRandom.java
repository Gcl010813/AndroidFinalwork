package com.xiaoguo.myapplication2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.xiaoguo.myapplication2.Entity.MealEntity;
import com.xiaoguo.myapplication2.ViewModel.MealViewModel;

public class MealRandom extends AppCompatActivity {
    private MealViewModel mealViewModel;
    private TextView MealName;
    private TextView MealDescription;
    private Button btn_randomMeal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_meal);

        btn_randomMeal = findViewById(R.id.buttonRandomMeal);
        MealName = findViewById(R.id.MealName);
        MealDescription=findViewById(R.id.MealDescription);

        mealViewModel = new ViewModelProvider(this).get(MealViewModel.class);

        btn_randomMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mealViewModel.getRandomMeal().observe(MealRandom.this, new Observer<MealEntity>() {
                    @Override
                    public void onChanged(MealEntity randomMeal) {
                        if (randomMeal != null) {
                            MealName.setText(randomMeal.getName());
                            MealDescription.setText(randomMeal.getDescription());
                        } else {
                            MealName.setText("餐品数据库为空,请先添加餐品");
                            MealDescription.setText("餐品数据库为空,请先添加餐品");
                        }
                    }
                });
            }
        });

    }
}