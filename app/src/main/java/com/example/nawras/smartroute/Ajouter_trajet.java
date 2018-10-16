package com.example.nawras.smartroute;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nawras.smartroute.Beans.Covoiturage;
import com.example.nawras.smartroute.WebMethods.AddCovoiturageService;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Ajouter_trajet extends Fragment {
    EditText depart, arrive, p_dep, p_des, datee, heure, prix, cond;
    Spinner nb_places;
    Button btn_ajout;
    SharedPreferences sharedPreferences;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        View view = inflater.inflate(R.layout.fragment_ajouter_trajet, container, false);
        depart = view.findViewById(R.id.Depart);
        arrive = view.findViewById(R.id.Arriver);
        p_dep = view.findViewById(R.id.PointDepart);
        p_des = view.findViewById(R.id.PointArriver);
        datee = view.findViewById(R.id.DateC);
        heure = view.findViewById(R.id.HeureC);
        prix = view.findViewById(R.id.prixEdit);
        cond = view.findViewById(R.id.Cond);
        nb_places = view.findViewById(R.id.spinn_Nbp);
        btn_ajout = view.findViewById(R.id.validerAjouterCovoiturage);
        btn_ajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((depart.getText().length() > 0) && (arrive.getText().length() > 0) &&
                        (p_dep.getText().length() > 0) && (p_des.getText().length() > 0) &&
                        (datee.getText().length() > 0) && (heure.getText().length() > 0) &&
                        (prix.getText().length() > 0)) {
                    String condd = "";
                    // TODO Get UserID from Shared Prefs
                    //------------BEGIN-------------------
                    int userID = sharedPreferences.getInt("userID", -1);
                    //------------END----------------//
                    // TODO Concat UserDate TIME
                    //------------BEGIN-------------------
                    Timestamp timestamp = null;
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");
                        Date parsedDate = dateFormat.parse(datee.getText().toString() + " " + heure.getText().toString());
                        timestamp = new java.sql.Timestamp(parsedDate.getTime());
                    } catch (Exception e) { //this generic but you can control another types of exception
                        // look the origin of excption
                    }
                    //------------END----------------//
                    if (cond.getText().length() > 0) {
                        condd = cond.getText().toString();
                    } else {
                        condd = "Aucun";
                    }
                    try {
                        Boolean resp = new AddCovoiturageService(getActivity()).execute(new Covoiturage(0,
                                depart.getText().toString(), arrive.getText().toString(), p_dep.getText().toString(),
                                p_des.getText().toString(), timestamp, Integer.parseInt(nb_places.getSelectedItem().toString()),
                                Double.parseDouble(prix.getText().toString()), condd, userID)).get();
                        if(resp)
                        {
                            Toast.makeText(getContext(),"Succes !",Toast.LENGTH_LONG).show();
                            Chercher_trajet newFragment = new Chercher_trajet();
                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                            transaction.replace(R.id.fram, newFragment);
                            transaction.addToBackStack(null);
                            transaction.commit();
                        }
                        else
                        {
                            //ERROR
                            Toast.makeText(getContext(),"Erreur !",Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {

                    }
                }
            }
        });
        return view;
    }
}