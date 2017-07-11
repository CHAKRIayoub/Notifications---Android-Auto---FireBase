package com.example.user.androidautonotification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by ayoub on 24/06/2017.
 */

public class FcmMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        //fonction pour afficher l'alert
        showNotification(remoteMessage.getData().get("message"));
    }


    //fonction pour afficher l'alert
    private void showNotification(String message) {

        /*Intent i = new Intent(this,MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,i, PendingIntent.FLAG_UPDATE_CURRENT);

        android.support.v4.app.NotificationCompat.Builder builder = new android.support.v4.app.NotificationCompat.Builder(this)
                .setAutoCancel(true)
                .setContentTitle("FCM Test")
                .setContentText(message)
                .setSmallIcon(R.drawable.cone)
                .setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        manager.notify(0,builder.build());*/


        //déclecher un broadcast reciver qui permet de lancer la notification phone et android auto
        Intent itnt = new Intent();
        itnt.setAction("com.example.user.androidautonotification.ACTION_MESSAGE_READ");

        itnt.putExtra("titre", message);
        itnt.putExtra("text", message);
        //itnt.putExtra("date", date);

        //decleché le broadcast recievr
        sendBroadcast(itnt);




    }

}
