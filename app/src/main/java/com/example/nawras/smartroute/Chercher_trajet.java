package com.example.nawras.smartroute;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.szagurskii.patternedtextwatcher.PatternedTextWatcher;


public class Chercher_trajet extends Fragment {
    Button btnChercher;
    EditText dateDep, empDep, empArr;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chercher_trajet, container, false);
        dateDep = (EditText) view.findViewById(R.id.editTextDateC);
        dateDep.addTextChangedListener(new PatternedTextWatcher("####-##-##"));
        empDep = (EditText) view.findViewById(R.id.editTextDepart);
        empArr = (EditText) view.findViewById(R.id.editTextArriver);
        btnChercher = (Button) view.findViewById(R.id.btnChercher);
        btnChercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((empDep.getText().length() > 0) &&
                        (empArr.getText().length() > 0) &&
                        (dateDep.getText().length() > 0)) {
                    Intent i = new Intent(getActivity(), ItemListActivity.class);
                    i.putExtra("dep",empDep.getText().toString());
                    i.putExtra("arr",empArr.getText().toString());
                    i.putExtra("time",dateDep.getText().toString());
                    startActivity(i);
                } else {
                    Toast.makeText(getContext(), "Saisir des critéres de recherche.", Toast.LENGTH_LONG).show();
                }

            }
        });
        return view;
    }

}