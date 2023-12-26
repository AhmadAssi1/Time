package com.example.time;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        EditText editTextName = findViewById(R.id.editTextName);
        EditText editTextEmail = findViewById(R.id.editTextEmail);
        EditText editTextPassword = findViewById(R.id.editTextPassword);
        EditText editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        Button buttonRegister = findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(v -> {
            String name = editTextName.getText().toString();
            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();
            String ConfirmPassword = editTextConfirmPassword.getText().toString();

            if(password.equals(ConfirmPassword)){
                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();

                // Save your string in SharedPreferences
                myEdit.putString("name", name);
                myEdit.putString("email", email);
                myEdit.putString("password", password);

                // Once you've added the values to the Editor, commit them back to the SharedPreferences:
                myEdit.apply(); // or myEdit.commit(); for synchronous saving

                // Handle what happens after saving data, e.g., showing a message or opening a new activity
                Intent intent = new Intent(Registration.this, MainActivity.class);
                startActivity(intent);

            }else{
                Toast.makeText(Registration.this, "Registration Failed Unmatched password", Toast.LENGTH_SHORT).show();
            }



        });
        TextView textViewLoginPrompt = findViewById(R.id.text1);

        textViewLoginPrompt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to open the RegistrationActivity
                Intent intent = new Intent(Registration.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}