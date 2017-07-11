package com.example.user.androidautonotification;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by ayoub on 24/06/2017.
 */

 //ce service permet de générer un id avec firebase
 //et partager avec les autres classe de notre projet


public class FcmIdService extends FirebaseInstanceIdService {


	//si le Token et changer cette fonction est déclenchée
    @Override
    public void onTokenRefresh() {

        String recent_token = FirebaseInstanceId.getInstance().getToken();

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(getString(R.string.FCM_PREF), Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getString(R.string.FCM_TOKEN), recent_token );
        //FCM_TOKEN : une simple string pour récupérer la valeur d'autre part du projet
        editor.commit();

    }
}






























