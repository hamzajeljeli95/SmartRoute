package com.example.nawras.smartroute.Beans;

import java.sql.Timestamp;

/**
 * Created by Hamza on 08/03/2018.
 */

public class Tools {
    public static String getServerAddr()
    {
        return "http://192.168.190.2:21987/";
    }

    public static String getMqttServerAddr()
    {
        return "tcp://192.168.190.2:1883";
    }

    public static int getRequestDelay() {
        return 2000;
    }

    public static String formatDateTime(Timestamp time)
    {
        return "";
    }
}
