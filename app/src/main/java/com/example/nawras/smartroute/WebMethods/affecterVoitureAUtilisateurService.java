package com.example.nawras.smartroute.WebMethods;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.example.nawras.smartroute.Beans.Tools;
import com.example.nawras.smartroute.Beans.Utilisateur;
import com.example.nawras.smartroute.R;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Hamza on 08/03/2018.
 */

public class affecterVoitureAUtilisateurService extends AsyncTask<Utilisateur,Void,Boolean> {


    ProgressDialog pd;

    public affecterVoitureAUtilisateurService(Activity mActivity) {
        pd = new ProgressDialog(mActivity);
        pd.setMessage(mActivity.getResources().getString(R.string.pwait));
        pd.setIndeterminate(false);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setCancelable(false);
    }

    @Override
    protected Boolean doInBackground(Utilisateur... utilisateurs) {
        Boolean res = false;
        try {
            final String url = Tools.getServerAddr() + "Users/addVeh/" + utilisateurs[0].getId() + "/" + utilisateurs[0].getVehId() + "/";//a changer
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
    protected void onCancelled() {
        if (pd.isShowing()) {
            pd.dismiss();
        }
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if (pd.isShowing()) {
            pd.dismiss();
        }
    }
}
