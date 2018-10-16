package com.example.nawras.smartroute;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import com.example.nawras.smartroute.Beans.Marque;
import com.example.nawras.smartroute.WebMethods.ListeMarques;


public class FullscreenActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if (sharedPreferences.getInt("userID", -1) != -1) {
                    intent = new Intent(getApplicationContext(), MainActivity.class);
                } else {
                    intent = new Intent(getApplicationContext(), Connexion.class);
                }
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}
