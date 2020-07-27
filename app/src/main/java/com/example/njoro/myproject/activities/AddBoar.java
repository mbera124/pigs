package com.example.njoro.myproject.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.njoro.myproject.R;
import com.example.njoro.myproject.model.Boar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;

public class AddBoar extends AppCompatActivity {
    EditText etbreed, etcolor, etweight, etservice, etmatimgsow, etmatingdate, etremainingdays, etage;
    Button btnsave;
    private FirebaseAuth auth;
    String userName;

    FirebaseDatabase database;
    DatabaseReference boars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_boar);
        etbreed = findViewById(R.id.breed);
        etcolor = findViewById(R.id.color);
        etage = findViewById(R.id.age);
        etweight = findViewById(R.id.weight);
        etservice = findViewById(R.id.service);
        etmatimgsow = findViewById(R.id.matingsow);
        etmatingdate = findViewById(R.id.matingdate);
        etremainingdays = findViewById(R.id.daysremaining);
        btnsave = findViewById(R.id.save);

        auth = FirebaseAuth.getInstance();


        String key = auth.getCurrentUser().getUid();
        database = FirebaseDatabase.getInstance();
        boars = database.getReference("Boar").child(key);
        userName = auth.getCurrentUser().getDisplayName();


        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showAlertDialog();
            }
        });



    }

    @Exclude
    private void showAlertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(AddBoar.this);
        alertDialog.setTitle("One more step!");
        alertDialog.setMessage("Enter Pig ID");
        final EditText edtpiggid = new EditText(AddBoar.this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        edtpiggid.setLayoutParams(layoutParams);
        alertDialog.setView(edtpiggid);
//        alertDialog.setIcon(R.drawable.cart);
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boars = boars.child(String.valueOf(System.currentTimeMillis()));

                        String boarKey = boars.push().getKey();
                        Boar boar = new Boar(
                                userName,
                                etbreed.getText().toString(),
                                etcolor.getText().toString(),
                                etage.getText().toString(),
                                etweight.getText().toString(),
                                etservice.getText().toString(),
                                etmatimgsow.getText().toString(),
                                etmatingdate.getText().toString(),
                                etremainingdays.getText().toString(),
                                edtpiggid.getText().toString()

                        );


                        boars.child("PigId").setValue(edtpiggid.getText().toString());
                        boars.child("Breed").setValue(etbreed.getText().toString());
                        boars.child("Color").setValue(etcolor.getText().toString());
                        boars.child("Age").setValue(etage.getText().toString());
                        boars.child("Weight").setValue(etweight.getText().toString());
                        boars.child("ServiceRecord").setValue(etservice.getText().toString());
                        boars.child("MatingSow").setValue(etmatimgsow.getText().toString());
                        boars.child("MatingDate").setValue(etmatingdate.getText().toString());
                        boars.child("RemainingDays").setValue(etremainingdays.getText().toString());

                        boars.push().setValue(boar);


                        Toast.makeText(AddBoar.this, "Your order has been placed", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });

        alertDialog.setNegativeButton("Back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }

}