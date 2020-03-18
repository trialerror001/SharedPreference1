package com.example.androidpreferences;

import androidx.appcompat.app.AppCompatActivity;

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
    private SharedPreferences sPreferences;
    private SharedPreferences.Editor sEditor;

    private EditText editUsername, editPassword;
    private Button btnLogin;
    private CheckBox chkRememberMe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editUsername = (EditText) findViewById(R.id.editUsername);
        editPassword = (EditText) findViewById(R.id.editPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        chkRememberMe = (CheckBox) findViewById(R.id.checkBox);
        sPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sEditor = sPreferences.edit();//Store the sharepreferences.

        checkSharedPreferences();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkRememberMe.isChecked()) {
                    sEditor.putString(getString(R.string.checkbox), "True");
                    sEditor.commit();

                    String name = editUsername.getText().toString();
                    sEditor.putString(getString(R.string.name), name);
                    sEditor.commit();

                    String password = editPassword.getText().toString();
                    sEditor.putString(getString(R.string.password), password);
                    sEditor.commit();
                }else{
                    sEditor.putString(getString(R.string.checkbox), "False");
                    sEditor.commit();

                    sEditor.putString(getString(R.string.name), "");
                    sEditor.commit();

                    sEditor.putString(getString(R.string.password), "");
                    sEditor.commit();
                }
            }
        });
    }

    private void checkSharedPreferences(){
        String checkbox = sPreferences.getString(getString(R.string.checkbox), "False");
        String username = sPreferences.getString(getString(R.string.name), "");
        String password = sPreferences.getString(getString(R.string.password), "");

        editUsername.setText(username);
        editPassword.setText(password);

        if(checkbox.equals("True"))
            chkRememberMe.setChecked(true);
        else
            chkRememberMe.setChecked(false);
    }
}
