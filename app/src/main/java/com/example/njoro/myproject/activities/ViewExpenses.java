package com.example.njoro.myproject.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.example.njoro.myproject.R;
import com.example.njoro.myproject.adapter.ExpensesAdapter;
import com.example.njoro.myproject.model.Expenses;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewExpenses extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    private FirebaseAuth auth;
    ProgressDialog mProgressDialog;
    RecyclerView recycler_expenses;
    RecyclerView.LayoutManager layoutManager;
    private ExpensesAdapter expensesAdapter;
    private List<Expenses> expensesList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_expenses);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Expenses").child(auth.getUid());

        expensesAdapter= new ExpensesAdapter(this, expensesList);

        //load menu
        recycler_expenses = findViewById(R.id.recycler_expenses);
        expensesAdapter = new ExpensesAdapter(this, expensesList);
        recycler_expenses.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 1);
        recycler_expenses.setLayoutManager(layoutManager);
        recycler_expenses.setAdapter(expensesAdapter);
        loadExpenses();

    }

    private void loadExpenses(){
        showProgressDialog();
        if(expensesList.size() >0){
            expensesList.clear();
        }
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot postSnapShot : dataSnapshot.getChildren()) {
                        if (postSnapShot != null) {
                            Expenses expenses = new Expenses();
                            expenses.setDate(postSnapShot.child("Date of Expense").getValue().toString());
                            expenses.setType(postSnapShot.child("Type of Expense").getValue().toString());
                            expenses.setAmount(postSnapShot.child("Amount Spent").getValue().toString());
                            expensesList.add(expenses);
                        }


                    }
                    //Toast.makeText(Home.this, "" + categoryList.size(), Toast.LENGTH_SHORT).show();

                    expensesAdapter.notifyDataSetChanged();
                }
                hideProgressDialog();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                hideProgressDialog();
            }
        });
    }
    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(true);
        }
        mProgressDialog.show();
    }


    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
