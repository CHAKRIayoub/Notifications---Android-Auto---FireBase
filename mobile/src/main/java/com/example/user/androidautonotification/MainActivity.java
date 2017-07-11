package com.example.user.androidautonotification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.RemoteInput;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import java.util.ArrayList;
import static android.R.id.list;

/**
 * Created by ayoub on 24/06/2017.
 */


public class MainActivity extends AppCompatActivity {

    ProgressBar progressbar;
    ListView lv;

    //URL du script php qui nous permet d'ajouter un id d'utilisateur
    //afin d'envoyer la notification a ce id 
    String server_url = "http://192.168.1.3/notf/register.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //MainActivity  :  une listeview pour afficher l'historique des notifications
        progressbar = (ProgressBar) findViewById(R.id.progressBar);
        lv = (ListView) findViewById(R.id.listview);
        ArrayList<NOtification> list = new ArrayList<NOtification>();
        CustomListAdapter cls = new CustomListAdapter( this,R.layout.lv_model ,list );
        lv.setAdapter(cls);

        //lacer l' asyncTask qui nous permet de remplire la liste d'historique des Notification
        new ClassAsyncT(cls, list, progressbar).execute();



        //récupérer l'id du telephone courant, générer par le service FcmIdService
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(getString(R.string.FCM_PREF), Context.MODE_PRIVATE);
        final String Token = sharedPreferences.getString(getString(R.string.FCM_TOKEN), "");
        
        // NB : SharedPreference nous permet de partager des variables avec les déffirent
        // classe de notre application

        //tester si l'id est récupéré correctement 
        Toast.makeText(this, Token, Toast.LENGTH_SHORT).show();


        //envoyer l'id au serveur avec volley
        ////afin d'envoyer la notification a ce id 
        StringRequest stringRequest = new StringRequest(Request.Method.GET, server_url + "?Token=" + Token , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        //envoyer la requete au serveur...
        Sngltn.getmInstance(MainActivity.this)
                .addToRQ(stringRequest);





    }

    //fonction du button Ajouter une Notification
    public void addNotf(View view) {
    	
        Intent i = new Intent(getApplicationContext(), Main2Activity.class );
        startActivity(i);

    }


    @Override
    protected void onResume() {
        super.onResume();
    }
}

