package com.example.vkcoin.repository;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import androidx.annotation.NonNull;

public class PushRepository {

    static Context context;
    private static volatile PushRepository instance;
    public static PushRepository getInstance(Context c) {
        context = c;
        PushRepository localInstance = instance;
        if (localInstance == null) {
            synchronized (PushRepository.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new PushRepository();
                }
            }
        }
        return localInstance;
    }

    public void initialize() {
//        FirebaseApp.initializeApp(context);
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("gsgjs", "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();
                        if(token == null) {
                            register(token);
                        }
                        Log.e("gsgjs", token);

                    }
                });
    }

    private void register(String token) {

    }
}
