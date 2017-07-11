package com.example.user.androidautonotification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by ayoub on 24/06/2017.
 */

//cette activity permet d' envoyer un alert au autre telephone grace a un script php

public class Main2Activity extends AppCompatActivity {

    EditText txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //le message d'alert
        txt = (EditText) findViewById(R.id.msgnotf);
    }

    //fonction de la bouton ajouter
    //permet d'envoyer une requete au serveur web ce derniere il envoir une requete JSON au service web de firebase ce dernier il envoi l'alert au téléphones 
    public void addnotf(View view) {

        //crée une nouvelle requete grace au volley
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://192.168.1.3/notf/push_notification.php?msg=" + txt.getText() , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println( response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            
                System.out.println(" "+error);

            }
        });

        //envoyer la requete
        Sngltn.getmInstance(Main2Activity.this)
                .addToRQ(stringRequest);

    }
}
