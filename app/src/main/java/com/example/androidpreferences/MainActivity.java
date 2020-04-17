package com.example.androidpreferences;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
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
                inputPreferences();
                String name = editName.getText().toString();
                String pass = editPassword.getText().toString();
                System.out.println("Username: "+name);
                System.out.println("Password: "+pass);
                if(name.equals("Admin") && pass.equals("Admin")){
                    Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                    i.putExtra("name", editName.getText().toString());
                    startActivity(i);
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setCancelable(false);
                    builder.setTitle("Login Failed");
                    builder.setMessage("Your username or password is incorrect");

                    builder.setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();
                }


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

  private void inputPreferences(){
        if(chkRememberme.isChecked()){
            mEditor.putString("username", editName.getText().toString());
            mEditor.putString("password", editPassword.getText().toString());
            if(chkRememberme.isChecked())
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

}
