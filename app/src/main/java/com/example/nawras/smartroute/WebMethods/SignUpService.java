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
 * Created by Hamza on 09/01/18.
 */

public class SignUpService extends AsyncTask<Utilisateur, Void, Integer> {

    ProgressDialog pd;

    public SignUpService(Activity mActivity) {
        pd = new ProgressDialog(mActivity);
        pd.setMessage(mActivity.getResources().getString(R.string.pwait));
        pd.setIndeterminate(false);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setCancelable(false);
    }

    @Override
    protected Integer doInBackground(Utilisateur... utilisateurs) {
        int res = -1;
        try {
            final String url = Tools.getServerAddr() + "Users/signup/" + utilisateurs[0].getNom() +
                    "/" + utilisateurs[0].getPrenom() +
                    "/" + utilisateurs[0].getDateNaissance() +
                    "/" + utilisateurs[0].getGenre() +
                    "/" + utilisateurs[0].getAddress() +
                    "/" + utilisateurs[0].getTelephone() +
                    "/" + utilisateurs[0].getEmail() +
                    "/" + utilisateurs[0].getMdp() + "/";//a changer
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory()).setConnectTimeout(Tools.getRequestDelay());
            res = restTemplate.postForObject(url, HttpMethod.POST, Integer.class);
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
    protected void onPostExecute(Integer integer) {
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
