package com.example.nawras.smartroute;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nawras.smartroute.Beans.Covoiturage;
import com.example.nawras.smartroute.Beans.Utilisateur;
import com.example.nawras.smartroute.WebMethods.GetUtilisateurService;
import com.google.gson.Gson;

import java.security.spec.ECField;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {

    private Covoiturage covoiturage;
    private TextView Pdep;
    private TextView Parr;
    private TextView Vdep;
    private TextView Varr;
    private TextView DateH;
    private TextView NBplace;
    private TextView prix;
    private TextView condition;
    private TextView name;

    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);
        String jsonObj = this.getArguments().getString("jsonCov");
        covoiturage = new Gson().fromJson(jsonObj, Covoiturage.class);
        Pdep = (TextView)rootView.findViewById(R.id.pointDep);
        Parr= (TextView)rootView.findViewById(R.id.pointArv);
        Vdep= (TextView)rootView.findViewById(R.id.villeDep);
        Varr= (TextView)rootView.findViewById(R.id.villeArv);
        DateH= (TextView)rootView.findViewById(R.id.DateHeure);
        NBplace= (TextView)rootView.findViewById(R.id.place);
        prix= (TextView)rootView.findViewById(R.id.prix);
        condition= (TextView)rootView.findViewById(R.id.condition);
        name= (TextView)rootView.findViewById(R.id.name);
        Pdep.setText(covoiturage.getPointDep());
        Parr.setText(covoiturage.getPointArr());
        Vdep.setText(covoiturage.getVilleDep());
        Varr.setText(covoiturage.getVilleArr());
        DateH.setText(covoiturage.getDateHeureDep().toString());
        NBplace.setText(covoiturage.getNbrPlaces().toString());
        prix.setText(covoiturage.getPrix().toString());
        condition.setText(covoiturage.getConditionCov());
        Utilisateur u = new Utilisateur();

        try
        {
            u =new GetUtilisateurService(getActivity()).execute(covoiturage.getIdUtilisateur()).get();
        }
        catch (Exception e)
        {
            u.setNom("STD ERR");
            u.setPrenom("STD ERR");
            e.printStackTrace();
        }
        name.setText(u.getNom().toString() + " "+ u.getPrenom().toString());
        return rootView;
    }
}
