package com.example.logpas;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {


    EditText writing_username;
    EditText writing_password;
    Button sign_in;
    Button sign_up;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        writing_username = (EditText) findViewById(R.id.username);
        writing_password = (EditText) findViewById(R.id.password);
        sign_in = (Button) findViewById(R.id.login);
        sign_up = (Button) findViewById(R.id.register);

        sign_in.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String write_username = writing_username.getText().toString();
                String write_password = writing_password.getText().toString();
                if (Users.get_pass(write_username, write_password, getApplicationContext())) {
                    startActivity(new Intent(MainActivity.this, SucActivity.class));
                } else {
                    startActivity(new Intent(MainActivity.this, FailActivity.class));
                }
            }
        });



        sign_up.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, RegActivity.class)));

    }
}