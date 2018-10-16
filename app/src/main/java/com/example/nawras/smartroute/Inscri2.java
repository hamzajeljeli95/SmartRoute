package com.example.nawras.smartroute;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nawras.smartroute.Beans.Categorie;
import com.example.nawras.smartroute.Beans.Motorisation;
import com.example.nawras.smartroute.Beans.Utilisateur;
import com.example.nawras.smartroute.Beans.Vehicule;
import com.example.nawras.smartroute.WebMethods.AddVehiculeService;
import com.example.nawras.smartroute.WebMethods.ListeCategorieService;
import com.example.nawras.smartroute.WebMethods.ListeMarques;
import com.example.nawras.smartroute.WebMethods.ListeModelesFromMarques;
import com.example.nawras.smartroute.WebMethods.ListeMotorisationService;
import com.example.nawras.smartroute.WebMethods.affecterVoitureAUtilisateurService;

import java.util.ArrayList;
import java.util.List;

public class Inscri2 extends AppCompatActivity {

    Spinner sp1, sp2, sp3, sp4;
    int newUserID;
    List<Categorie> categorieList;
    List<Motorisation> motorisationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscri2);
        sp1 = findViewById(R.id.sp1);
        newUserID = getIntent().getIntExtra("newId", -1);
        if (newUserID == -1) {
            //TODO CRASH
        }
        List<String> list = new ArrayList<String>();
        categorieList = new ArrayList<Categorie>();
        try {
            categorieList = new ListeCategorieService(this).execute().get();
            for (Categorie categorie : categorieList) {
                list.add(categorie.getLibCat());
            }
        } catch (Exception e) {
            System.out.println("Phase 1 crashed.");
            e.printStackTrace();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adapter);
        //----
        List<String> list2 = new ArrayList<String>();
        sp2 = findViewById(R.id.sp2);
        motorisationList = new ArrayList<Motorisation>();
        try {
            motorisationList = new ListeMotorisationService(this).execute().get();
            for (Motorisation motorisation : motorisationList) {
                list2.add(motorisation.getLibMot());
            }
        } catch (Exception e) {
            System.out.println("Phase 2 crashed.");
            e.printStackTrace();
        }
        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, list2);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp2.setAdapter(adapter);
        //-----
        sp3 = findViewById(R.id.sp3);
        sp4 = findViewById(R.id.sp4);
        List<String> list3 = new ArrayList<String>();
        List<String> BrandList = new ArrayList<String>();
        try {
            BrandList = new ListeMarques(this).execute().get();
        } catch (Exception e) {
            System.out.println("Phase 3 crashed.");
            e.printStackTrace();
        }
        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, BrandList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp3.setAdapter(adapter);
        sp3.setSelection(0);
        sp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                List<String> ModelsList = new ArrayList<String>();
                try {
                    ModelsList = new ListeModelesFromMarques(Inscri2.this).execute(sp3.getSelectedItem().toString()).get();
                } catch (Exception e) {
                    System.out.println("Phase 3 crashed.");
                    e.printStackTrace();
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_spinner_item, ModelsList);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp4.setAdapter(adapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void creer(View v) {
        Vehicule vehicule = new Vehicule();
        vehicule.setImgVoiture("VOID");
        vehicule.setMarque(sp3.getSelectedItem().toString());
        vehicule.setModele(sp4.getSelectedItem().toString());
        for (Categorie categorie : categorieList) {
            if (categorie.getLibCat().equals(sp1.getSelectedItem().toString())) {
                vehicule.setCatId(categorie.getId());
                break;
            }
        }
        for (Motorisation motorisation : motorisationList) {
            if (motorisation.getLibMot().equals(sp2.getSelectedItem().toString())) {
                vehicule.setMotId(motorisation.getId());
                break;
            }
        }
        int vehId = -1;
        boolean resp = false;
        try {
            vehId = new AddVehiculeService(this).execute(vehicule).get();
            resp = new affecterVoitureAUtilisateurService(this).execute(new Utilisateur(newUserID, vehId)).get();
            if (resp) {
                Toast.makeText(this, "Succes !", Toast.LENGTH_SHORT).show();
                postSignUp();
            } else {
                Toast.makeText(this, "Erreur !", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ignorer(View v) {
        postSignUp();
    }

    void postSignUp() {
        Toast.makeText(this,"Veuillez vous connecter avec votre nouvelle compte.",Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, Connexion.class));
        finish();
    }

}
