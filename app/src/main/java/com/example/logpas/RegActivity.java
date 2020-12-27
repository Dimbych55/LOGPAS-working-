package com.example.logpas;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegActivity extends AppCompatActivity {
    EditText registered_username;
    EditText registered_password;
    EditText registered_password_again;
    Button registered;
    TextView errors;

    private void check_valid_data(){
        String error_out = "";

        if (!registered_password.getText().toString().equals(registered_password_again.getText().toString()))
            error_out += "\n" +getResources().getString(R.string.different_password );
        registered.setEnabled(error_out.isEmpty());
        errors.setText(error_out);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        registered_username = (EditText) findViewById(R.id.registrationUsername);
        registered_password = (EditText) findViewById(R.id.registrationPassword);
        registered_password_again = (EditText) findViewById(R.id.registrationPassword2);
        registered = (Button) findViewById(R.id.registration);
        errors = (TextView) findViewById(R.id.textErrors);
        TextWatcher checker = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                check_valid_data();}
            @Override
            public void afterTextChanged(Editable editable) {}};

        registered_username.addTextChangedListener(checker);
        registered_password_again.addTextChangedListener(checker);
        registered_password.addTextChangedListener(checker);


        registered.setOnClickListener((OnClickListener) view -> {
            String login =  registered_username.getText().toString();
            String password = registered_password.getText().toString();
            Users.set_pass(login, password, getApplicationContext());
            final Dialog dialog = new Dialog(RegActivity.this);
            dialog.setContentView(R.layout.message);
            Button ok_button = (Button) dialog.findViewById(R.id.button);
            ok_button.setOnClickListener(view1 -> {
                dialog.dismiss();
                finish();

            });
            dialog.setCancelable(false);
            dialog.show();


        });

    }
}