package com.example.valarmorghulis.firebaseauth;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;

public class NetworkConnection {

    public static final String ERR_DIALOG_TITLE = "No internet connection detected !";
    private static final String ERR_DIALOG_MSG = "Looks like our application is not able to detect an active internet connection, " +
            "please check your device's network settings.";
    private static final String ERR_DIALOG_POSITIVE_BTN = "Settings";
    private static final String ERR_DIALOG_NEGATIVE_BTN = "Dismiss";

    public static boolean isConnectedToInternet(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		/*
		Class that answers queries about the state of network connectivity.
		It also notifies applications when network connectivity changes.
		The primary responsibilities of this class are to:
		Monitor network connections (Wi-Fi, GPRS, UMTS, etc.)
		Send broadcast intents when network connectivity changes
		Attempt to "fail over" to another network when connectivity to a network is lost
		Provide an API that allows applications to query the coarse-grained or fine-grained state of the available networks
		Provide an API that allows applications to request and select networks for their data traffic
		*/
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		//Use ConnectivityManager#getActiveNetworkInfo() to get an instance that represents the current network connection.
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }


    public static boolean isConnectedToWifi(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null &&
                networkInfo.isConnectedOrConnecting() &&
                networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
    }


    public static boolean isConnectedToMobileNetwork(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null &&
                networkInfo.isConnectedOrConnecting() &&
                networkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
    }

    public static void showNoInternetAvailableErrorDialog(final Context context) {
        new AlertDialog.Builder(context)
                .setTitle(ERR_DIALOG_TITLE)
                .setMessage(ERR_DIALOG_MSG)
                .setIcon(R.drawable.ic_wifi)
                .setPositiveButton(ERR_DIALOG_POSITIVE_BTN, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                        context.startActivity(intent);
                    }
                })
                .setNegativeButton(ERR_DIALOG_NEGATIVE_BTN, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        if (isConnectedToInternet(context)
                                || isConnectedToMobileNetwork(context)
                                || isConnectedToWifi(context)) {

                        } else {
                            showNoInternetAvailableErrorDialog(context);
                            return;
                        }
                        /*((FragmentActivity) context)
                                .getSupportFragmentManager()
                                .beginTransaction().replace(R.id.frag_container,new HomeFragment())
                                .commit();*/
                    }
                })
                .show();
    }
}
