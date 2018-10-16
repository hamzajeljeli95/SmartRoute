package com.example.nawras.smartroute.WebMethods;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.example.nawras.smartroute.Beans.Message;
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
 * Created by Hamza on 22/04/18.
 */

public class ListeUserMessages extends AsyncTask<Integer,Void,List<Message>> {
    ProgressDialog pd;

    public ListeUserMessages(Activity mActivity) {
        pd = new ProgressDialog(mActivity);
        pd.setMessage(mActivity.getResources().getString(R.string.pwait));
        pd.setIndeterminate(false);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setCancelable(false);
    }

    @Override
    protected List<Message> doInBackground(Integer... integers) {
        List<Message> messageList = new ArrayList<Message>();
        try {
            final String url = Tools.getServerAddr() + "Messages/list/"+integers[0]+"/";//a changer
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory()).setConnectTimeout(Tools.getRequestDelay());
            messageList =  Arrays.asList(restTemplate.postForObject(url, HttpMethod.POST, Message[].class));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return messageList;
    }

    @Override
    protected void onPreExecute() {
        pd.show();
    }

    @Override
    protected void onPostExecute(List<Message> messages) {
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
