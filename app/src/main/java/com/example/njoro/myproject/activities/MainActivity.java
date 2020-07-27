package com.example.njoro.myproject.activities;

import android.content.DialogInterface;
import android.content.Intent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.njoro.myproject.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setMessage("Which Category Do You Belong To?");
        alertDialog.setPositiveButton("Farmer", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                startActivity(new Intent(MainActivity.this, LogIn.class));
                finish();


            }
        });
        alertDialog.setNegativeButton("Buyer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainActivity.this, BuyerActivity.class);
                startActivity(intent);
                finish();
                dialog.dismiss();
            }
        });
        alertDialog.show();

    }
}
