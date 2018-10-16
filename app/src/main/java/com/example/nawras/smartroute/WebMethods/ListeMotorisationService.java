package com.example.nawras.smartroute.WebMethods;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.example.nawras.smartroute.Beans.Motorisation;
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

public class ListeMotorisationService extends AsyncTask<Void,Void,List<Motorisation>> {

    ProgressDialog pd;

    public ListeMotorisationService(Activity mActivity) {
        pd = new ProgressDialog(mActivity);
        pd.setMessage(mActivity.getResources().getString(R.string.pwait));
        pd.setIndeterminate(false);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setCancelable(false);
    }

    @Override
    protected List<Motorisation> doInBackground(Void... voids) {
        List<Motorisation> motorisations = new ArrayList<Motorisation>();
        try {
            final String url = Tools.getServerAddr() + "Motor/list/";//a changer
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory()).setConnectTimeout(Tools.getRequestDelay());
            motorisations = Arrays.asList(restTemplate.postForObject(url, HttpMethod.POST, Motorisation[].class));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return motorisations;
    }

    @Override
    protected void onPreExecute() {
        pd.show();
    }

    @Override
    protected void onPostExecute(List<Motorisation> motorisations) {
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
