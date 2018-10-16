package com.example.nawras.smartroute.WebMethods;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.example.nawras.smartroute.Beans.Tools;
import com.example.nawras.smartroute.Beans.Vehicule;
import com.example.nawras.smartroute.R;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Hamza on 08/03/2018.
 */

public class AddVehiculeService extends AsyncTask<Vehicule, Void, Integer> {

    ProgressDialog pd;

    public AddVehiculeService(Activity mActivity) {
        pd = new ProgressDialog(mActivity);
        pd.setMessage(mActivity.getResources().getString(R.string.pwait));
        pd.setIndeterminate(false);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setCancelable(false);
    }

    @Override
    protected Integer doInBackground(Vehicule... vehicules) {
        int res = -1;
        try {
            final String url = Tools.getServerAddr() + "Cars/add/" + vehicules[0].getCatId() + "/"
                    + vehicules[0].getMotId() + "/"
                    + vehicules[0].getMarque() + "/"
                    + vehicules[0].getModele() + "/"
                    + vehicules[0].getImgVoiture() + "/";//a changer
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
    protected void onCancelled() {
        if (pd.isShowing()) {
            pd.dismiss();
        }
    }

    @Override
    protected void onPostExecute(Integer integer) {
        if (pd.isShowing()) {
            pd.dismiss();
        }
    }
}
