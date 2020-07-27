package com.example.njoro.myproject.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.njoro.myproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogIn extends AppCompatActivity {
    EditText edtemail,edtpassword;
    Button btnsignin,btnsignup;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        auth = FirebaseAuth.getInstance();

        edtemail = findViewById(R.id.edtemail);
        edtpassword = findViewById(R.id.edtpassword);
        btnsignin = findViewById(R.id.btnsignin);
        btnsignup = findViewById(R.id.btnsignup);
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(LogIn.this, AdminActivity.class));
            finish();
        }
        //init firebase
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        final DatabaseReference table_user = database.getReference("User");

        btnsignup.setOnClickListener(v -> {
            startActivity(new Intent(LogIn.this, SignUp.class));
            finish();
        });

        btnsignin.setOnClickListener(v -> {

            String email = edtemail.getText().toString();
            final String password = edtpassword.getText().toString();
            if (TextUtils.isEmpty(email)) {
                edtemail.setError("Enter E-Mail");
                edtemail.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(password)) {
                edtpassword.setError("Enter Password");
                edtpassword.requestFocus();
                return;
            }

            final ProgressDialog mDialog = new ProgressDialog(LogIn.this);
            mDialog.setMessage("Signing In...");
            mDialog.show();

            //authenticate user
            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {
                            // there was an error
                            if (password.length() < 6) {
                                edtpassword.setError("Password too short");
                            } else {
                                mDialog.dismiss();
                                Toast.makeText(LogIn.this, "Authentication failed, check your email and password or sign up", Toast.LENGTH_LONG).show();
                            }

                        } else {
                            mDialog.dismiss();
//                            private void showAlertDialog() {
                            startActivity(new Intent(LogIn.this, AdminActivity.class));
                            finish();

                        }
                        }

                         });
    });
    }

//    @Override
//    public void onBackPressed() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(LogIn.this);
//        builder.setTitle(R.string.app_name);
//        builder.setIcon(R.mipmap.ic_launcher);
//        builder.setMessage("Do you want to exit?")
//                .setCancelable(false)
//                .setPositiveButton("Yes", (dialog, id) -> finish())
//                .setNegativeButton("No", (dialog, id) -> dialog.cancel());
//        AlertDialog alert = builder.create();
//        alert.show();
//
//    }

}
