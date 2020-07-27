package com.example.njoro.myproject.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.njoro.myproject.R;
import com.example.njoro.myproject.model.Expenses;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;

public class AddExpenses extends AppCompatActivity {
    EditText etdate, ettype, etamount;
    Button btnsave;
    private FirebaseAuth auth;
    String userName;

    FirebaseDatabase database;
    DatabaseReference expenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenses);
        etdate = findViewById(R.id.expensedate);
        ettype = findViewById(R.id.expensetype);
        etamount = findViewById(R.id.amount);
        btnsave = findViewById(R.id.save);
        auth = FirebaseAuth.getInstance();


        String key = auth.getCurrentUser().getUid();
        database = FirebaseDatabase.getInstance();
        expenses = database.getReference("Expenses").child(key);
        userName = auth.getCurrentUser().getDisplayName();

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                expenses = expenses.child(String.valueOf(System.currentTimeMillis()));

                String expenseKey = expenses.push().getKey();
                Expenses expenses1 = new Expenses(
                        userName,
                        etdate.getText().toString(),
                        ettype.getText().toString(),
                        etamount.getText().toString()


                );


                expenses.child("Date of Expense").setValue(etdate.getText().toString());
                expenses.child("Type of Expense").setValue(ettype.getText().toString());
                expenses.child("Amount Spent").setValue(etamount.getText().toString());


                expenses.push().setValue(expenses1);


                Toast.makeText(AddExpenses.this, "Your Expense Has Been Added!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}