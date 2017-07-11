package com.example.user.androidautonotification;


import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by ayoub on 24/06/2017.
 */


//une classe simple permet  d'envoyer et gérer les réquétes volley

public class Sngltn {


    private static Sngltn mInstance;
    private static Context mContext;
    private RequestQueue requestQueue;



    private Sngltn(Context context){

        mContext = context;
        requestQueue = getRequestQueue();

    }



    private RequestQueue getRequestQueue(){

        if (requestQueue == null){

            requestQueue = Volley.newRequestQueue(mContext.getApplicationContext());

        }
        return requestQueue;

    }



    public static synchronized Sngltn getmInstance(Context context){

        if (mInstance == null){

            mInstance = new Sngltn(context);

        }

        return mInstance;

    }



    public<T> void  addToRQ(Request<T> request){

        getRequestQueue().add(request);

    }

}
