package com.example.nawras.smartroute;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nawras.smartroute.Beans.Utilisateur;
import com.example.nawras.smartroute.WebMethods.LoginService;

public class Connexion extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private EditText emailFeild, passwordFeild;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        emailFeild = (EditText) findViewById(R.id.mail);
        passwordFeild = (EditText) findViewById(R.id.password);
    }

    public void userLogin(View view) {
        try {
            if ((emailFeild.getText().toString().length() > 0) && (passwordFeild.getText().toString().length() > 0)) {
                int res = new LoginService(this).execute(new Utilisateur(emailFeild.getText().toString(), passwordFeild.getText().toString())).get();
                if (res != -1) {
                    sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
                    editor = sharedPreferences.edit();
                    editor.putInt("userID",res);
                    editor.commit();
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(this, "Connection echoué.", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "Champs vides detectées.", Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            Toast.makeText(this, "Error !", Toast.LENGTH_LONG).show();
        }
    }

    public void retour(View v) {
        Intent intent = new Intent(this, Inscri.class);
        startActivity(intent);
    }
}
