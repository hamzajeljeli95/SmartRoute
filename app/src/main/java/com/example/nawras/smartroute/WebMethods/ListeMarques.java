package com.example.nawras.smartroute.WebMethods;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.example.nawras.smartroute.Beans.Categorie;
import com.example.nawras.smartroute.Beans.Marque;
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
 * Created by Hamza on 08/03/2018.
 */

public class ListeMarques extends AsyncTask<Void,Void,List<String>> {

    @Override
    protected List<String> doInBackground(Void... voids) {
        List<String> marques = new ArrayList<String>();
        try {
            final String url = Tools.getServerAddr() +"ListeCars/list/";
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory()).setConnectTimeout(Tools.getRequestDelay());
            marques = Arrays.asList(restTemplate.postForObject(url, HttpMethod.POST,String[].class));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return marques;
    }

    ProgressDialog pd;

    public ListeMarques(Activity mActivity) {
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
    protected void onCancelled() {
        if (pd.isShowing()) {
            pd.dismiss();
        }
    }

    @Override
    protected void onPostExecute(List<String> i) {
        if (pd.isShowing()) {
            pd.dismiss();
        }
    }
}
