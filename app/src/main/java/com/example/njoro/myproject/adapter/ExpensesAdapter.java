package com.example.njoro.myproject.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.njoro.myproject.R;
import com.example.njoro.myproject.model.Expenses;
import com.example.njoro.myproject.model.Sow;
import com.example.njoro.myproject.viewholder.ExpensesViewHolder;

import java.util.List;

public class ExpensesAdapter  extends RecyclerView.Adapter<ExpensesViewHolder>{
    private List<Expenses> expensesList;
    private Context mContext;
    private String TAG = "ExpensesAdapter";
    public ExpensesAdapter(Context context, List<Expenses> expensesList) {
        this.mContext = context;
        this.expensesList =expensesList;

    }


    @NonNull
    @Override
    public ExpensesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.viewexpenses_layout, parent, false);
        return new ExpensesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpensesViewHolder holder, int position) {
        Expenses expenses =expensesList.get(position);
        holder.date.setText(expenses.getDate());
        holder.type.setText(expenses.getType());
        holder.amount.setText(expenses.getAmount());
        holder.cvexpensesitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick: clicked on: " + expensesList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return expensesList==null?0:expensesList.size();
    }
}
