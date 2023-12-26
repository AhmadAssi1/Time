package com.example.time;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class mainPage extends AppCompatActivity {
    private Button Date;
    private Button Day;
    private Button logeOut;
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Date = findViewById(R.id.Date);
        Day = findViewById(R.id.Day);
        logeOut = findViewById(R.id.logOut);
        Date.setOnClickListener(view -> {
            Intent intent = new Intent(mainPage.this, DatePage.class);
            startActivity(intent);
        });
        Day.setOnClickListener(view -> {
            Intent intent = new Intent(mainPage.this, TimeZon.class);
            startActivity(intent);
        });
        logeOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = false;
                SaveFlag();
                Intent intent = new Intent(mainPage.this, MainActivity.class);
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
}