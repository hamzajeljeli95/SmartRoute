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
 * Created by Hamza on 25/04/18.
 */

public class GetUtilisateurService extends AsyncTask<Integer,Void,Utilisateur> {

    ProgressDialog pd;

    public GetUtilisateurService(Activity mActivity) {
        pd = new ProgressDialog(mActivity);
        pd.setMessage(mActivity.getResources().getString(R.string.pwait));
        pd.setIndeterminate(false);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setCancelable(false);
    }

    @Override
    protected void onPreExecute() {
        pd.show();
    }

    @Override
    protected void onPostExecute(Utilisateur utilisateur) {
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

    @Override
    protected Utilisateur doInBackground(Integer... integers) {
        Utilisateur res = new Utilisateur(-1);
        try {
            final String url = Tools.getServerAddr() + "Users/getUserData/" + integers[0] + "/";//a changer
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory()).setConnectTimeout(Tools.getRequestDelay());
            res = restTemplate.postForObject(url, HttpMethod.POST, Utilisateur.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
