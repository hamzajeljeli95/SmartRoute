package com.example.nawras.smartroute.WebMethods;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.example.nawras.smartroute.Beans.Categorie;
import com.example.nawras.smartroute.Beans.Covoiturage;
import com.example.nawras.smartroute.Beans.Tools;
import com.example.nawras.smartroute.R;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Hamza on 05/04/18.
 */

public class ListeCovoiturageService extends AsyncTask<Covoiturage,Void,List<Covoiturage>> {

    ProgressDialog pd;

    public ListeCovoiturageService(Activity mActivity) {
        pd = new ProgressDialog(mActivity);
        pd.setMessage(mActivity.getResources().getString(R.string.pwait));
        pd.setIndeterminate(false);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setCancelable(false);
    }

    @Override
    protected List<Covoiturage> doInBackground(Covoiturage... covoiturages) {
        List<Covoiturage> covList = new ArrayList<Covoiturage>();
        try {
            final String url = Tools.getServerAddr() + "Cov/list/"+covoiturages[0].getVilleDep()+"/"+covoiturages[0].getVilleArr()+"/"+covoiturages[0].getDateHeureDep().toString()+"/";//a changer
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory()).setConnectTimeout(Tools.getRequestDelay());
            covList = Arrays.asList(restTemplate.postForObject(url, HttpMethod.POST, Covoiturage[].class));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return covList;
    }

    @Override
    protected void onPreExecute() {
        pd.show();
    }


    @Override
    protected void onCancelled() {
        if (pd.isShowing()) {
            pd.dismiss();
        }
    }

    @Override
    protected void onPostExecute(List<Covoiturage> i) {
        if (pd.isShowing()) {
            pd.dismiss();
        }
    }
}
