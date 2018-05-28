package com.learning.gptw.greatplacetowork_learning.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.learning.gptw.greatplacetowork_learning.Constans.Constans;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;


/**
 * @author rodrigo.cruz
 * Network Utility to check network conectivity an status
 */
public class NetworkUtil {

    private static final String LOGGER_TAG = NetworkUtil.class.getSimpleName();

    /**
     * Check if the device is connected to WiFi
     *
     * @param context
     * @return true if the device is connected to WiFi network otherwise return false
     */
    public static boolean isConnectedWifi(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        boolean evalResult = networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
        Log.d(LOGGER_TAG, "Checking if the device is connected to WiFi Network.... [" + evalResult + "]");
        return evalResult;
    }

    /**
     * Check if the device is connected to Mobile
     *
     * @param context
     * @return true if the device is connected to mobile network otherwise return false
     */
    public static boolean isConnectedMobile(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        boolean evalResult = networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
        Log.d(LOGGER_TAG, "Checking if the device is connected to Mobile Network.... [" + evalResult + "]");
        return evalResult;
    }


    /**
     * Check the status of the communication whit GPTW web Api services
     * @param context
     * @return
     */
    public static boolean isOnline(Context context) {

        RunnableFuture<Boolean> futureRun =
                new FutureTask<>(
                        new NetworkTestConnectionCallUtil(
                                Constans.servicesURL,
                                1500,
                                200,
                                context)
                );
        new Thread(futureRun).start();

        try {
            boolean evalResult = futureRun.get();
            Log.d(LOGGER_TAG, "Checking the communication whit GPTW API services ... [" + evalResult + "]");
            return evalResult;
        } catch (InterruptedException | ExecutionException e) {
            Log.e(LOGGER_TAG, "Error in the communication whit GPTW API services ["+Constans.servicesURL+"]",e);
            return false;
        }
    }
}


