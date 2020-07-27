package com.example.njoro.myproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.njoro.myproject.R;

public class AdminActivity extends AppCompatActivity {
TextView tvfinance,tvpigs;
Button btnaddexpense,btnviewexpense,btnaddsales,btnviewsales,btnsellpig,btnaddsow,btnaddboar,btnviewboar,btnviewsow,btnpigsonsale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        tvfinance=findViewById(R.id.finance);
        tvpigs=findViewById(R.id.PIGS);
        btnaddexpense=findViewById(R.id.addexpense);
        btnviewexpense=findViewById(R.id.viewexpense);
        btnaddsales=findViewById(R.id.addsales);
        btnsellpig=findViewById(R.id.sellpig);
        btnviewsales=findViewById(R.id.viewsales);
        btnaddboar=findViewById(R.id.addboar);
        btnviewboar=findViewById(R.id.viewboar);
        btnaddsow=findViewById(R.id.addsow);
        btnviewsow=findViewById(R.id.viewsow);
        btnpigsonsale=findViewById(R.id.pigsonsale);

        btnsellpig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this,SellPig.class));
//                finish();
            }
        });

        btnaddsow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, AddSow.class));
//                finish();
            }
        });
        btnaddboar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this,AddBoar.class));
//                finish();
            }
        });
        btnaddexpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this,AddExpenses.class));
//                finish();
            }
        });
        btnaddsales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this,AddSales.class));
//                finish();
            }
        });

        btnviewboar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, ViewBoar.class));
            }
        });
        btnviewsow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, ViewSow.class));
            }
        });
        btnviewexpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, ViewExpenses.class));
            }
        });
        btnviewsales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, ViewSales.class));
            }
        });
        btnpigsonsale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this,BuyerActivity.class));
//                finish();
            }
        });

    }
}
