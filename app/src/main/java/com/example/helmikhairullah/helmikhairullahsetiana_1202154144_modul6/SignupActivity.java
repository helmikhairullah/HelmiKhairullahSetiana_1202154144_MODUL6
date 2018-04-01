package com.example.helmikhairullah.helmikhairullahsetiana_1202154144_modul6;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;


public class SignupActivity extends AppCompatActivity {

    private EditText namaaku, namakamu;
    private Button Daftar;
    private TextView aku, kamu;
    private FirebaseAuth auth;
    private ProgressDialog lalu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

            //Get firebase auth intance
        auth = FirebaseAuth.getInstance();

        namaaku = (EditText) findViewById(R.id.username1);
        namakamu = (EditText) findViewById(R.id.pass1);
        Daftar = (Button) findViewById(R.id.signup);
        aku = (TextView) findViewById(R.id.username);
        kamu = (TextView) findViewById(R.id.pass);

        lalu = new ProgressDialog(this);


        Daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = namaaku.getText().toString().trim();
                String password = namakamu.getText().toString().trim();


                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Masukkan alamat email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Masukkan password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "password kamu terlalu lemah", Toast.LENGTH_SHORT).show();
                    return;
                }

                startActivity(new Intent(SignupActivity.this, LoginActivity.class));

            lalu.setMessage("Sign Up");
                lalu.show();

                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {



                                //jika sig in gagal , akan menampilkan message ke user
                                // auth state listener akan menampilkan notifikasi dan logic to handle
                                // signed in user dapat di tangani di dalam listener

                                if (!task.isSuccessful()){
                                    Toast.makeText(SignupActivity.this, "autentikasi gagal" + task.getException(),
                                            Toast.LENGTH_SHORT).show();

                                } else {
                                    startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                                    finish();
                                }
                            }
                        }


                );

            }


        })
    ;}

}