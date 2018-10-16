package com.example.nawras.smartroute.WebMethods;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.example.nawras.smartroute.Beans.Covoiturage;
import com.example.nawras.smartroute.Beans.Tools;
import com.example.nawras.smartroute.R;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Hamza on 08/03/2018.
 */

public class AddCovoiturageService extends AsyncTask<Covoiturage, Void, Boolean> {

    ProgressDialog pd;

    public AddCovoiturageService(Activity mActivity) {
        pd = new ProgressDialog(mActivity);
        pd.setMessage(mActivity.getResources().getString(R.string.pwait));
        pd.setIndeterminate(false);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setCancelable(false);
    }

    @Override
    protected Boolean doInBackground(Covoiturage... covoiturages) {
        Boolean res = false;
        try {
            final String url = Tools.getServerAddr() + "Cov/add/" + covoiturages[0].getVilleDep() +
                    "/" + covoiturages[0].getVilleArr() +
                    "/" + covoiturages[0].getPointDep() +
                    "/" + covoiturages[0].getPointArr() +
                    "/" + covoiturages[0].getDateHeureDep() +
                    "/" + covoiturages[0].getNbrPlaces() +
                    "/" + covoiturages[0].getPrix() +
                    "/" + covoiturages[0].getConditionCov() +
                    "/" + covoiturages[0].getIdUtilisateur() + "/";//a changer
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory()).setConnectTimeout(Tools.getRequestDelay());
            res = restTemplate.postForObject(url, HttpMethod.POST, Boolean.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    protected void onPreExecute() {
        pd.show();
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if (pd.isShowing()) {
            pd.dismiss();
        }
    }

    @Override
    protected void onCancelled() {
        if (pd.isShowing()) {
            pd.dismiss();
        }
    }


}
