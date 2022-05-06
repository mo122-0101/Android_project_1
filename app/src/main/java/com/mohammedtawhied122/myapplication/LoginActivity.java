package com.mohammedtawhied122.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    DBHelper dbHelper;
    TextInputLayout username, password;
    Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//      Hooks For Username, Password, LoginButton
        username = findViewById(R.id.loginUsername);
        password = findViewById(R.id.loginPassword);
        loginButton = findViewById(R.id.loginButton);
    }

    private boolean checkUsername() {
        String val = username.getEditText().getText().toString().trim();
        if(val.isEmpty()) {
            username.setError("field cann't be empty");
            return false;
        }else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    private boolean checkPassword() {
        String val = password.getEditText().getText().toString().trim();
        if(val.isEmpty()) {
            password.setError("field cann't be empty");
            return false;
        }else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    private void isUser() {
        String enteredUsername = username.getEditText().getText().toString().trim();
        String enteredPassword = password.getEditText().getText().toString().trim();

        ArrayList<Patient> patientArrayList = new ArrayList<>();
        patientArrayList = dbHelper.selectByPassword(enteredPassword);

        if(!patientArrayList.isEmpty()) {
            for (Patient patient: patientArrayList) {
                String idFromDB = patient.getPatientId();
                String usernameFromDB = patient.getPatientUsername();
                String passwordFromDB = patient.getPatientPassword();

                Intent intent = new Intent(LoginActivity.this, PatientActivity.class);
                intent.putExtra("id", idFromDB);
                intent.putExtra("username", usernameFromDB);
                intent.putExtra("password", passwordFromDB);
                startActivity(intent);
            }
        }else {
            password.setError("wrong password");
            password.requestFocus();
        }
    }

    public void zeftMethod(View view) {
        if(!checkUsername() | !checkPassword()) {
            return;
        }else {
            isUser();
        }
    }

}