package polus.ddns.net.chelinfo.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;

import java.util.ArrayList;

import polus.ddns.net.chelinfo.beans.GetBeansFromRest;
import polus.ddns.net.chelinfo.beans.NewsListItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Игорь on 23.11.2016.
 */

public class NetworkUtils {
    /**
     * Checks if network is available at this time
     */
    public static Boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }
}