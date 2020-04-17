package com.example.androidpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    Button btnMateri, btnSoal, btnGallery, btnEntertainment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnMateri = (Button) findViewById(R.id.btnMateri);
        btnSoal = (Button) findViewById(R.id.btnSoal);


        //lblUser.setText(getIntent().getExtras().getString("name"));

        PlayBackgroundSound();
        btnMateri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MateriActivity.class));
            }
        });

        btnSoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SoalActivity.class));
            }
        });
    }

    public void PlayBackgroundSound(){
        startService(new Intent(HomeActivity.this, MusicBackground.class));
    }
}
