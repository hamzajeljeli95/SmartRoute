package com.example.nawras.smartroute;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.nawras.smartroute.Beans.Utilisateur;
import com.example.nawras.smartroute.WebMethods.SignUpService;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Inscri extends AppCompatActivity {

    EditText nom, prenom, email, mdp, dtns, addr, tel;
    RadioButton rd_homme, rd_femme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscri);
        nom = findViewById(R.id.editTextNom);
        prenom = findViewById(R.id.editTextPreNom);
        email = findViewById(R.id.editTextEmail);
        mdp = findViewById(R.id.editTextMp);
        dtns = findViewById(R.id.editTextdate);
        addr = findViewById(R.id.editTextAdress);
        tel = findViewById(R.id.editTextPhone);
        rd_homme = findViewById(R.id.rd_h);
        rd_femme = findViewById(R.id.rd_f);
    }


    public void suivant(View v) {
        try {
            DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            Date date = (Date) formatter.parse(dtns.getText().toString());
            String genre = "";
            if (rd_femme.isChecked()) {
                genre = rd_femme.getText().toString();
            } else {
                genre = rd_homme.getText().toString();
            }
            if ((nom.getText().toString().length() > 0) && (prenom.getText().toString().length() > 0) &&
                    (email.getText().toString().length() > 0) && (mdp.getText().toString().length() > 0) &&
                    (dtns.getText().toString().length() > 0) && (addr.getText().toString().length() > 0) &&
                    (tel.getText().toString().length() > 0)) {

                int newUserID = new SignUpService(this).execute(new Utilisateur(0, 0, nom.getText().toString(),
                        prenom.getText().toString(),
                        new Timestamp(date.getTime()),
                        genre,
                        addr.getText().toString(),
                        Long.parseLong(tel.getText().toString()),
                        email.getText().toString(),
                        mdp.getText().toString(),
                        "None", "utilisateur")).get();
                if (newUserID!=-1)
                {
                    Intent intent = new Intent(this, Inscri2.class);
                    intent.putExtra("newId",newUserID);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(this, "Echoué.", Toast.LENGTH_LONG).show();
                }
            }
            else
            {
                Toast.makeText(this, "Champs vides detectées.", Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            System.out.println("DEBUG : ERROR\n" + e.getMessage());
            e.printStackTrace();
        }
    }
}
