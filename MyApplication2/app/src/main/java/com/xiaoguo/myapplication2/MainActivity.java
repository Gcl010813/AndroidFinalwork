package com.xiaoguo.myapplication2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button AddMeal,RandomMeal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AddMeal = findViewById(R.id.AddMeal);
        AddMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddMealActivity();
            }
        });


        RandomMeal = findViewById(R.id.RandomMeal);
        RandomMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRandomMealActivity();
            }
        });
    }

    private void openAddMealActivity() {
        Intent intent = new Intent(MainActivity.this, MealAdd.class);
        startActivity(intent);
    }

    private void openRandomMealActivity() {
        Intent intent = new Intent(MainActivity.this, MealRandom.class);
        startActivity(intent);
    }
}