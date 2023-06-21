package com.xiaoguo.myapplication2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.xiaoguo.myapplication2.Entity.MealEntity;
import com.xiaoguo.myapplication2.ViewModel.MealViewModel;
import java.util.List;

public class MealAdd extends AppCompatActivity {
    private EditText editTextMealName;
    private EditText editTextMealDescription;
    private Button addMeal;
    private RecyclerView recyclerView;
    private MealViewModel mealViewModel;
    private MealAdapter mealAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);

        editTextMealName = findViewById(R.id.MealName);
        editTextMealDescription = findViewById(R.id.MealDescription);
        addMeal = findViewById(R.id.AddMeal);
        recyclerView = findViewById(R.id.ListMeals);

        mealViewModel = new ViewModelProvider(this).get(MealViewModel.class);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mealAdapter = new MealAdapter(mealViewModel);
        recyclerView.setAdapter(mealAdapter);

        mealViewModel = new ViewModelProvider(this).get(MealViewModel.class);
        mealViewModel.getAllMeals().observe(this, new Observer<List<MealEntity>>() {
            @Override
            public void onChanged(List<MealEntity> meals) {
                mealAdapter.setMeals(meals);
                mealAdapter.notifyDataSetChanged();
            }
        });

        addMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mealName = editTextMealName.getText().toString().trim();
                String mealDescription = editTextMealDescription.getText().toString().trim();
                if (!mealName.isEmpty()&&!mealDescription.isEmpty()) {
                    MealEntity meal = new MealEntity(mealName, mealDescription);
                    mealViewModel.insertMeal(meal);
                    Toast.makeText(MealAdd.this, "添加成功", Toast.LENGTH_SHORT).show();
                    editTextMealName.setText("");
                    editTextMealDescription.setText("");
                } else {
                    Toast.makeText(MealAdd.this, "添加失败,请重试", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}