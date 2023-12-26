package com.example.time;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private boolean flag;
    private CheckBox checkBox ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textViewLoginPrompt = findViewById(R.id.text1);
        EditText editTextEmail = findViewById(R.id.editTextEmail);
        EditText editTextPassword = findViewById(R.id.editTextPassword);
        Button buttonLogin = findViewById(R.id.buttonLogin);
         checkBox = findViewById(R.id.checkBox);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve user input
                String inputEmail = editTextEmail.getText().toString();
                String inputPassword = editTextPassword.getText().toString();

                // Get SharedPreferences object
                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);

                // Retrieve data from SharedPreferences
                String savedEmail = sharedPreferences.getString("email", "");
                String savedPassword = sharedPreferences.getString("password", "");

                // Check if the input matches the saved data
                if (inputEmail.equals(savedEmail) && inputPassword.equals(savedPassword)) {
                    flag = checkBox.isChecked();
                    SaveFlag();
                    // Login success
                    Intent intent = new Intent(MainActivity.this, mainPage.class);
                    startActivity(intent);
                } else {
                    // Login failed
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }

        });
        textViewLoginPrompt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to open the RegistrationActivity
                Intent intent = new Intent(MainActivity.this, Registration.class);
                startActivity(intent);
            }
        });


    }
    public void SaveFlag(){
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        // Save your string in SharedPreferences
        myEdit.putBoolean("flag", flag);



        // Once you've added the values to the Editor, commit them back to the SharedPreferences:
        myEdit.apply();

    }

    @Override
    protected void onStart() {
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        // Retrieve data from SharedPreferences
        boolean rememberFlag = sharedPreferences.getBoolean("flag",false );
        if(rememberFlag){
            Intent intent = new Intent(MainActivity.this, mainPage.class);
            startActivity(intent);
        }

        super.onStart();
    }
}
