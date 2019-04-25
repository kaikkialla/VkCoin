package com.example.vkcoin;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class PushService extends FirebaseMessagingService {
    public PushService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if(remoteMessage != null && remoteMessage.getNotification() != null) {
            showNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
        }
    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
    }

    private void showNotification(String title, String body){

    }
}
