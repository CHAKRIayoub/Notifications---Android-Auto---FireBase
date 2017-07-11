package com.example.user.androidautonotification;

/**
 * Created by ayoub on 24/06/2017.
 */

//une simple classe notification utilisée par l'adpter du listview 

public class NOtification {

    public String text;
    public String titre;
    public  String date;

    public NOtification( String t, String tt, String d ){

        titre = t;
        text = tt;
        date = d;

    }


}
