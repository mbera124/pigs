package com.example.njoro.myproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.njoro.myproject.R;
import com.example.njoro.myproject.model.Sales;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;

public class AddSales extends AppCompatActivity {
    EditText etdate,ettype,etamount;
    Button btnsave;

    private FirebaseAuth auth;
    String userName;

    FirebaseDatabase database;
    DatabaseReference sales;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sales);
        etdate=findViewById(R.id.saledate);
        ettype=findViewById(R.id.saletype);
        etamount=findViewById(R.id.saleamount);
        btnsave=findViewById(R.id.save);
        auth = FirebaseAuth.getInstance();


        String key = auth.getCurrentUser().getUid();
        database = FirebaseDatabase.getInstance();
        sales = database.getReference("Sale").child(key);
        userName = auth.getCurrentUser().getDisplayName();

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sales = sales.child(String.valueOf(System.currentTimeMillis()));

                String saleKey = sales.push().getKey();
                Sales sales1= new Sales(
                        userName,
                        etdate.getText().toString(),
                        ettype.getText().toString(),
                        etamount.getText().toString()


                        );


                sales.child("Sale Date").setValue(etdate.getText().toString());
                sales.child("Sale Type").setValue(ettype.getText().toString());
                sales.child("Amount Received").setValue(etamount.getText().toString());


                sales.push().setValue(sales1);


                Toast.makeText(AddSales.this, "Your Sale Has Been Added!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}