package com.learning.gptw.greatplacetowork_learning.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;


/**
 * Clase que se encarga de probar la conexion a una URL en especifico
 * @author rodrigo.cruz
 * @since 23/05/2018
 */
public class NetworkTestConnectionCallUtil implements Callable<Boolean> {

    private  static final String LOGGER_TAG =  NetworkTestConnectionCallUtil.class.getSimpleName();


    /**
     *URL to test
     */
    private String testUrl;
    /**
     *Connection timeout
     */
    private Integer connectionTimeout;
    /**
     *Response code expected from the URL connect call
     */
    private Integer expectedResponseCode;
    /**
     * Current App context in device
     */
    private Context context;


    /**
     * Private to avoid creating instances without the constructor with arguments
     */
    private NetworkTestConnectionCallUtil(){
        //No instance
    }

    /**
     * Build and assign values ​​to make the call
     * @param testUrl
     * @param connectionTimeout
     * @param expectedResponseCode
     * @param context
     */
    public NetworkTestConnectionCallUtil(String testUrl,Integer connectionTimeout,Integer expectedResponseCode,Context context){
        this.testUrl = testUrl;
        this.connectionTimeout = connectionTimeout;
        this.expectedResponseCode = expectedResponseCode;
        this.context =  context;
    }



    /**
     * Call to test Network
     * @return True if the connection is successful
     */
    @Override
    public Boolean call() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if ((networkInfo.isAvailable()) && (networkInfo.isConnected())) {
            try {
                HttpURLConnection urlc = (HttpURLConnection) (new URL(this.testUrl).openConnection());
                urlc.setRequestProperty("User-Agent", "Test");
                urlc.setRequestProperty("Connection", "close");
                urlc.setConnectTimeout(this.connectionTimeout);
                urlc.connect();
                return (urlc.getResponseCode() == this.expectedResponseCode);
            } catch (IOException e) {
                Log.e(LOGGER_TAG, "Error checking connection ["+this.testUrl+"]", e);
            }
        } else {
            Log.d(LOGGER_TAG, "No network available!");
        }
        return false;
    }

}
