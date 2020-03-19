package com.example.androidpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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
    CheckBox chkRememberMe;
    SharedPreferences mPreferences;
    SharedPreferences.Editor mEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = (EditText) findViewById(R.id.editUsername);
        editPassword = (EditText) findViewById(R.id.editPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        chkRememberMe = (CheckBox) findViewById(R.id.checkBox);

        mPreferences = getSharedPreferences("LoginInfo", Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();

        checkPreferences();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkRememberMe.isChecked()){
                    mEditor.putString("username", editName.getText().toString());
                    mEditor.putString("password", editPassword.getText().toString());
                    if(chkRememberMe.isChecked())
                        mEditor.putString("checkbox", "True");
                    else
                        mEditor.putString("checkbox", "False");
                    mEditor.commit();
                }else{
                    mEditor.putString("username", "");
                    mEditor.putString("password", "");
                    mEditor.putString("checkbox", "False");
                    mEditor.commit();
                }

            }
        });

    }

    private void checkPreferences(){
        editName.setText(mPreferences.getString("username", ""));
        editPassword.setText(mPreferences.getString("password", ""));
        if (mPreferences.getString("checkbox","").equals("True"))
            chkRememberMe.setChecked(true);
        else
            chkRememberMe.setChecked(false);
    }
}
