package com.tejeet.beets.network;

import android.util.Log;

import androidx.annotation.NonNull;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyMessegingService extends FirebaseMessagingService {


    private static final String TAG = "tag";



    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.

            Log.d(TAG, "Message data payload: " + remoteMessage.getData());



            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());


    }

    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        String userToken = s.toString();

        Log.d("tag","Got New Token "+userToken);

    }




}
