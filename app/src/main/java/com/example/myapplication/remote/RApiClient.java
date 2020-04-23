package com.example.myapplication.remote;

import android.app.Activity;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Harsh on 23/10/19.
 */
public class RApiClient {

    public static final String BASE_URL = "https://www.webdesky.com/wordpress/kouponhub/webapi/webservices/"; //
    private static Retrofit retrofit = null;


    public static RApiInterface getClient(Class<RApiInterface> apiInterfaceClass, Activity activity) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(apiInterfaceClass);
    }

}
