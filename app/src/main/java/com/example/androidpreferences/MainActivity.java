package com.example.androidpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editName, editPassword;
    Button btnLogin;
    CheckBox chkRememberme;
    SharedPreferences mPreferences;
    SharedPreferences.Editor mEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = (EditText) findViewById(R.id.editUsername);
        editPassword =(EditText) findViewById(R.id.editPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        chkRememberme = (CheckBox) findViewById(R.id.checkBox);

        mPreferences = getSharedPreferences("LoginInfo", Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();

        checkPreferences();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
        });
  }

  private void checkPreferences(){
       editName.setText(mPreferences.getString("username", ""));
       editPassword.setText(mPreferences.getString("password", ""));
       if(mPreferences.getString("checkbox", "").equals("True"))
           chkRememberme.setChecked(true);
       else
           chkRememberme.setChecked(false);
  }
}
