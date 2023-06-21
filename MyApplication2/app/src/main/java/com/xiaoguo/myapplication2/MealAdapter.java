package com.xiaoguo.myapplication2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.xiaoguo.myapplication2.Entity.MealEntity;
import com.xiaoguo.myapplication2.ViewModel.MealViewModel;
import java.util.ArrayList;
import java.util.List;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MealViewHolder> {
    private List<MealEntity> meals = new ArrayList<>();
    private MealViewModel mealViewModel;

    public MealAdapter(MealViewModel mealViewModel) {
        this.mealViewModel = mealViewModel;
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_meal, parent, false);
        return new MealViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MealViewHolder holder, int position) {
        MealEntity meal = meals.get(position);
        holder.bind(meal);
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public void setMeals(List<MealEntity> meals) {
        this.meals = meals;
        notifyDataSetChanged();
    }

    public class MealViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewMealName;
        private TextView textViewMealDescription;

        public MealViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewMealName = itemView.findViewById(R.id.textViewMealName);
            textViewMealDescription = itemView.findViewById(R.id.textViewMealDescription);


            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    showDeleteConfirmationDialog(getAdapterPosition());
                    return false;
                }
            });
        }

        public void bind(MealEntity meal) {
            textViewMealName.setText(meal.getName());
            textViewMealDescription.setText(meal.getDescription());
        }
        private void showDeleteConfirmationDialog(final int position) {
            AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
            builder.setTitle("!!!警告!!!")
                    .setMessage("?确认删除?")
                    .setPositiveButton("删除", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            deleteMeal(position);
                        }
                    })
                    .setNegativeButton("取消", null)
                    .show();
        }

        private void deleteMeal(int position) {
            if (position != RecyclerView.NO_POSITION) {
                MealEntity meal = meals.get(position);
                mealViewModel.deleteMeal(meal);
            }
        }
    }
}